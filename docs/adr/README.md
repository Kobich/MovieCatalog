# Architecture Decision Records (ADR)

## Активные решения

| №                                          | Название                                                                         | 
|--------------------------------------------|----------------------------------------------------------------------------------|
| [0001](0001-use-adr.md)                    | Использование ADR для документирования архитектурных решений                     | 
| [0002](0003-feature-api-impl.md)           | Разделение Gradle-фич-модулей на `api` и `impl`                                  | 
| [0003](0004-feature-api.md)                | Понятие фичи и единая точка взаимодействия (`XxxFeature`), структура API-моделей | 
| [0004](0004-feature-шьзд.md)               | Внутренняя структура `impl`-модулей                                              | 
| [0005](0006-feature-grouping.md)           | Варианты группировки фичей                                                       | 
| [0006](0007-shared-api-isolation.md)       | Модуль `feature:shared-api` и изоляция фичей                                     | 
| [0007](0007-feature-naming-conventions.md) | Конвенции именования feature-модулей                                             | 

---

## Что такое ADR?

Architecture Decision Records (ADR) - это документы, которые фиксируют важные архитектурные решения,
принятые в ходе разработки проекта, а также обсуждений на техсинках команды, вместе с их контекстом
и обоснованием.

## Структура файлов

```
docs/adr/
├── README.md              # Этот файл - описание процесса ADR
├── template.md            # Шаблон для создания новых ADR
├── 0001-use-adr.md       # Первый ADR о самом использовании ADR
├── 0002-example.md       # Следующие ADR
└── ...
```

---

## Полезные ссылки

- [ADR GitHub Organization](https://adr.github.io/) - Ресурсы по ADR
- [Documenting Architecture Decisions](https://cognitect.com/blog/2011/11/15/documenting-architecture-decisions) -
  Оригинальная статья Michael Nygard
- [ADR Tools](https://github.com/npryce/adr-tools) - Инструменты для работы с ADR

