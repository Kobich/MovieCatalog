package com.wbprofit.core.utils.secure.api

/**
 * Abstraction for persisting small sensitive values using Android Keystore backed AES/GCM encryption.
 * Designed for secrets such as the WB API key entered by the user.
 */
interface SecureKeystoreStorage {
    /**
     * Encrypts and stores [value] under the provided [key].
     *
     * @return `true` when the value was persisted without crypto errors.
     */
    fun save(key: String, value: String): Boolean

    /**
     * Reads and decrypts the value stored for [key].
     *
     * @return decrypted value or `null` when the key is missing or the payload is corrupted.
     */
    fun read(key: String): String?

    /**
     * Removes the entry for [key].
     *
     * @return `true` when the entry was removed successfully.
     */
    fun remove(key: String): Boolean

    /** Clears every stored entry. */
    fun clear()

    /** @return `true` when there is an encrypted value mapped to [key]. */
    fun contains(key: String): Boolean
}
