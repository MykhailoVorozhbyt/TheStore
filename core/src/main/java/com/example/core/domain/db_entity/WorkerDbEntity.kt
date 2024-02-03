package com.example.core.domain.db_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.utils.enums.WorkerAccess

@Entity(tableName = WorkerDbEntity.TABLE_NAME)
data class WorkerDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID) val id: Long = 0,
    @ColumnInfo(name = COLUMN_ACCESS) val access: Int = WorkerAccess.Employee.access,
    @ColumnInfo(name = COLUMN_PHOTO) val photoUri: String? = null,
    @ColumnInfo(name = COLUMN_NAME) val name: String,
    @ColumnInfo(name = COLUMN_SURNAME) val surname: String,
    @ColumnInfo(name = COLUMN_PASSWORD) val password: String,
    @ColumnInfo(name = COLUMN_PHONE) val phone: String,
    @ColumnInfo(name = COLUMN_MAIL) val emailAddress: String = "",
) {
    companion object {
        const val TABLE_NAME = "workerTable"
        const val COLUMN_ID = "id"
        const val COLUMN_ACCESS = "access"
        const val COLUMN_PHOTO = "photo"
        const val COLUMN_NAME = "name"
        const val COLUMN_SURNAME = "surname"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_MAIL = "emailAddress"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WorkerDbEntity

        if (id != other.id) return false
        if (access != other.access) return false
        if (photoUri != null) {
            if (other.photoUri == null) return false
            if (!photoUri.contentEquals(other.photoUri)) return false
        } else if (other.photoUri != null) return false
        if (name != other.name) return false
        if (surname != other.surname) return false
        if (password != other.password) return false
        if (phone != other.phone) return false
        return emailAddress == other.emailAddress
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + access.hashCode()
        result = 31 * result + (photoUri?.hashCode() ?: 0)
        result = 31 * result + name.hashCode()
        result = 31 * result + surname.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + phone.hashCode()
        result = 31 * result + emailAddress.hashCode()
        return result
    }
}
