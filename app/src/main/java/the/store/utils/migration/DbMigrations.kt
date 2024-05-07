package the.store.utils.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.core.domain.db_entity.CompanyDbEntity
import com.example.core.domain.db_entity.ProductDbEntity
import com.example.core.domain.db_entity.SaleHistoryDbEntity
import com.example.core.domain.db_entity.check.CheckDbEntity
import com.example.core.domain.db_entity.check.CheckProductDbEntity

val MIGRATION_1_2 by lazy {
    object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL(
                "CREATE TABLE IF NOT EXISTS ${CompanyDbEntity.TABLE_NAME} " +
                        "(${CompanyDbEntity.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "${CompanyDbEntity.COLUMN_PHOTO} TEXT, " +
                        "${CompanyDbEntity.COLUMN_NAME} TEXT NOT NULL, " +
                        "${CompanyDbEntity.COLUMN_DESCRIPTION} TEXT NOT NULL)"
            )
        }
    }
}

val MIGRATION_2_3 by lazy {
    object : Migration(2, 3) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL(
                "ALTER TABLE ${CompanyDbEntity.TABLE_NAME} " +
                        "ADD COLUMN ${CompanyDbEntity.COLUMN_COMPANY_CREATED} INTEGER NOT NULL DEFAULT 0"
            )
        }
    }
}

val MIGRATION_3_4 by lazy {
    object : Migration(3, 4) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL(
                "ALTER TABLE ${CompanyDbEntity.TABLE_NAME} " +
                        "ADD COLUMN ${CompanyDbEntity.COLUMN_CREATED_AT} INTEGER NOT NULL DEFAULT 0"
            )
        }
    }
}

val MIGRATION_4_5 by lazy {
    object : Migration(4, 5) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL(
                "CREATE TABLE IF NOT EXISTS ${SaleHistoryDbEntity.TABLE_NAME} " +
                        "(${SaleHistoryDbEntity.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "${SaleHistoryDbEntity.COLUMN_SALE_ID} INTEGER NOT NULL , " +
                        "${SaleHistoryDbEntity.COLUMN_CREATED_AT} INTEGER NOT NULL , " +
                        "${SaleHistoryDbEntity.COLUMN_FULL_PRICE} REAL NOT NULL , " +
                        "${SaleHistoryDbEntity.COLUMN_PRODUCTS} INTEGER NOT NULL)"
            )
        }
    }
}

val MIGRATION_5_6 by lazy {
    object : Migration(5, 6) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE ${ProductDbEntity.TABLE_NAME} ADD COLUMN temp_measurementId INTEGER NOT NULL DEFAULT 0")
            db.execSQL("UPDATE ${ProductDbEntity.TABLE_NAME} SET temp_${ProductDbEntity.COLUMN_MEASUREMENT_ID} = ${ProductDbEntity.COLUMN_MEASUREMENT_ID}")
            db.execSQL("ALTER TABLE ${ProductDbEntity.TABLE_NAME} DROP COLUMN ${ProductDbEntity.COLUMN_MEASUREMENT_ID}")
            db.execSQL("ALTER TABLE ${ProductDbEntity.TABLE_NAME} RENAME COLUMN temp_${ProductDbEntity.COLUMN_MEASUREMENT_ID} TO ${ProductDbEntity.COLUMN_MEASUREMENT_ID}")

        }
    }
}

val MIGRATION_6_7 by lazy {
    object : Migration(6, 7) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL(
                "ALTER TABLE ${ProductDbEntity.TABLE_NAME} " +
                        "ADD COLUMN ${ProductDbEntity.COLUMN_CURRENCY_ID} INTEGER NOT NULL DEFAULT 0"
            )
        }
    }
}

val MIGRATION_7_8 by lazy {
    object : Migration(7, 8) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("DROP TABLE IF EXISTS ${CheckDbEntity.TABLE_NAME}")
            db.execSQL("DROP TABLE IF EXISTS ${CheckProductDbEntity.TABLE_NAME}")

            db.execSQL("CREATE TABLE IF NOT EXISTS ${CheckDbEntity.TABLE_NAME} " +
                    "(${CheckDbEntity.COLUMN_ID} INTEGER PRIMARY KEY NOT NULL, " +
                    "${CheckDbEntity.COLUMN_CHECK_PRICE} REAL NOT NULL, " +
                    "${CheckDbEntity.COLUMN_CREATED_AT} INTEGER NOT NULL, " +
                    "${CheckDbEntity.COLUMN_IS_CANCELED} INTEGER NOT NULL)")

            db.execSQL("CREATE TABLE IF NOT EXISTS ${CheckProductDbEntity.TABLE_NAME} " +
                    "(${CheckProductDbEntity.COLUMN_CHECK_ID} INTEGER PRIMARY KEY NOT NULL, " +
                    "${CheckProductDbEntity.COLUMN_PHOTO_URI} TEXT, " +
                    "${CheckProductDbEntity.COLUMN_NAME} TEXT NOT NULL, " +
                    "${CheckProductDbEntity.COLUMN_PRICE} REAL NOT NULL, " +
                    "${CheckProductDbEntity.COLUMN_FULL_PRICE} REAL NOT NULL, " +
                    "${CheckProductDbEntity.COLUMN_QUANTITY} REAL NOT NULL, " +
                    "${CheckProductDbEntity.COLUMN_MEASUREMENT_ID} INTEGER NOT NULL, " +
                    "${CheckProductDbEntity.COLUMN_CURRENCY_ID} INTEGER NOT NULL)")

        }
    }
}