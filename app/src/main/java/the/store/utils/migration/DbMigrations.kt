package the.store.utils.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.core.domain.db_entity.CompanyDbEntity
import com.example.core.domain.db_entity.SaleHistoryDbEntity

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
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

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE ${CompanyDbEntity.TABLE_NAME} " +
                "ADD COLUMN ${CompanyDbEntity.COLUMN_COMPANY_CREATED} INTEGER NOT NULL DEFAULT 0")
    }
}

val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE ${CompanyDbEntity.TABLE_NAME} " +
                "ADD COLUMN ${CompanyDbEntity.COLUMN_CREATED_AT} INTEGER NOT NULL DEFAULT 0")
    }
}

val MIGRATION_4_5: Migration = object : Migration(4, 5) {
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