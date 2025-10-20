# 0007. Конвенции именования feature-модулей

Дата: 13.10.2025

## Статус

Принято

## Контекст

Для обеспечения единообразия и предсказуемости архитектуры необходимо установить чёткие правила именования
Gradle-модулей, пакетов, классов и управления видимостью в feature-модулях.

Отсутствие единых конвенций приводит к:

- Непредсказуемой структуре проекта
- Сложностям в навигации по коду
- Нарушению принципов инкапсуляции
- Проблемам с поддержкой и развитием кодовой базы

## Решение

Устанавливаем единые конвенции именования для всех элементов feature-модулей.

### 1. Именование Gradle-модулей

**Базовая структура:**

```
:feature:<name>:api
:feature:<name>:impl
```

**Правила:**

- Название из одного слова: `:feature:payments:api`, `:feature:payments:impl`
- Название из нескольких слов: `:feature:password-reset:api`, `:feature:password-reset:impl`

*Примечание: о группировке фичей см. ADR [0006](0006-feature-grouping.md)*

**Принципы:**

- Названия из нескольких слов разделяются **дефисами** в Gradle-модулях
- Каждая фича имеет свои `api` и `impl` модули

### 2. Именование пакетов

**Базовая структура:**

```
<package>.feature.<feature.name>
<package>.feature.<feature.name>.impl
```

**Правила:**

- Название из одного слова: `<package>.feature.payments`
- Название из нескольких слов: `<package>.feature.password.reset`
- Группировка в папках: `<package>.feature.auth.email` (соответствует структуре папок)

**Принцип:** Названия из нескольких слов разделяются **точками** в пакетах. Camel case и подчёркивания не используется.

### 3. Именование классов и интерфейсов

**Основные интерфейсы фичей:**

- Формат: `XxxFeature`
- Примеры: `PaymentsFeature`, `AuthFeature`, `UserProfileFeature`

**Реализации:**

- Формат: `XxxFeatureImpl`
- Примеры: `PaymentsFeatureImpl`, `AuthFeatureImpl`
- Модификатор: `internal class` (кроме случаев, когда нужен доступ из app)

**Модели данных (entity):**

- События: `PaymentEvent`, `AuthEvent`
- Состояния: `PaymentState`, `AuthState`
- Данные: `PaymentData`, `UserData`

**Внутренние компоненты:**

- Interactors: `PaymentInteractor`, `AuthInteractor`
- Repositories: `PaymentRepository`, `AuthRepository`
- Repository Impl: `PaymentRepositoryImpl`, `AuthRepositoryImpl`

### 4. Управление видимостью (visibility)

#### Что должно быть `public`:

- Все классы и интерфейсы в `api`-модулях
- DI-модули в `impl` (импортируются в app)

#### Что должно быть `internal`:

- Все реализации фичей: `XxxFeatureImpl`
- Все классы в пакетах `domain/` и `data/`
- Все внутренние компоненты (интеракторы, репозитории)
- Вспомогательные классы и утилиты

**Пример:**

```kotlin
// api-модуль (public)
interface PaymentsFeature {
    suspend fun processPayment(request: PaymentRequest): PaymentResult
}

data class PaymentRequest(val amount: Money, val userId: UserId)

// impl-модуль (internal)
internal class PaymentsFeatureImpl(
    private val paymentInteractor: PaymentInteractor
) : PaymentsFeature {
    // реализация
}

internal class PaymentInteractor(
    private val repository: PaymentRepository
) {
    // бизнес-логика
}

// DI-модуль (public - импортируется в app)
val paymentFeatureModule = module {
    single<PaymentsFeature> { PaymentsFeatureImpl(get()) }

    // Domain
    single<PaymentInteractor> { PaymentInteractor(get()) }
    single<PaymentRepository> { PaymentRepositoryImpl(get(), get()) }

    // Data
    single<PaymentNetworkApi> { get<AuthNetworkFeature>().create() }
    single { PaymentLocalStorage(get()) }
}
```

### 5. Специальные случаи

#### Группировка связанных фичей в папках

```
feature/
└── auth/                            # Папка для группировки auth-фичей
    ├── email/
    │   ├── api/                     # API-модуль :feature:auth:email:api
    │   │   ├── EmailAuthFeature.kt  # Пакет: <package>.feature.auth.email
    │   │   └── entity/
    │   │       └── EmailModels.kt   # Пакет: <package>.feature.auth.email.entity
    │   └── impl/                    # Impl-модуль :feature:auth:email:impl
    │       └── EmailAuthFeatureImpl.kt # Пакет: <package>.feature.auth.email.impl
    └── social/
        ├── api/                     # API-модуль :feature:auth:social:api
        │   ├── SocialAuthFeature.kt # Пакет: <package>.feature.auth.social
        │   └── entity/
        │       └── SocialModels.kt  # Пакет: <package>.feature.auth.social.entity
        └── impl/                    # Impl-модуль :feature:auth:social:impl
            └── SocialAuthFeatureImpl.kt # Пакет: <package>.feature.auth.social.impl
```

### 6. Примеры полных путей

| Тип                              | Gradle-модуль                  | Пакет                                     | Класс                      |
|----------------------------------|--------------------------------|-------------------------------------------|----------------------------|
| Название из одного слова API     | `:feature:payments:api`        | `<package>.feature.payments`            | `PaymentsFeature`          |
| Название из одного слова Impl    | `:feature:payments:impl`       | `<package>.feature.payments.impl`       | `PaymentsFeatureImpl`      |
| Название из нескольких слов API  | `:feature:password-reset:api`  | `<package>.feature.password.reset`      | `PasswordResetFeature`     |
| Название из нескольких слов Impl | `:feature:password-reset:impl` | `<package>.feature.password.reset.impl` | `PasswordResetFeatureImpl` |
| Группировка в папке API          | `:feature:auth:email:api`      | `<package>.feature.auth.email`          | `EmailAuthFeature`         |
| Группировка в папке Impl         | `:feature:auth:email:impl`     | `<package>.feature.auth.email.impl`     | `EmailAuthFeatureImpl`     |
| Shared модели                    | `:feature:shared-api`          | `<package>.feature.shared.entity`       | `UserId`, `Money`          |

### 7. Контрольный чек-лист

При создании новой фичи проверьте:

- [ ] Gradle-модуль следует паттерну `:feature:<name>:api` и `:feature:<name>:impl`
- [ ] Пакеты используют `<package>.feature.*` префикс
- [ ] Основной интерфейс называется `XxxFeature`
- [ ] Реализация называется `XxxFeatureImpl` и помечена `internal`
- [ ] Модели размещены в пакете `entity`
- [ ] DI-модуль публичный, остальное в impl — `internal`
- [ ] Сложные имена используют дефисы в Gradle и точки в пакетах
- [ ] При группировке в папках пакеты соответствуют структуре папок

## Последствия

**Положительные:**

- Единообразная и предсказуемая структура проекта
- Упрощение навигации и поиска компонентов
- Чёткое разделение публичного API и внутренней реализации
- Автоматическая самодокументация через именование
- Упрощение code review и onboarding новых разработчиков

**Отрицательные:**

- Требуется дисциплина при соблюдении конвенций
- Необходимость рефакторинга существующего кода
- Потенциальные длинные имена пакетов для сложных фичей
