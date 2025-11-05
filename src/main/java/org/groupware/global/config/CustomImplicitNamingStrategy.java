package org.groupware.global.config;

import org.hibernate.boot.model.naming.*;
import org.hibernate.boot.model.source.spi.AttributePath;
import org.hibernate.boot.model.source.spi.AttributeRole;

public class CustomImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {

    @Override
    public Identifier determineUniqueKeyName(ImplicitUniqueKeyNameSource source) {
        String tableName = source.getTableName().getText();
        String columnName = source.getColumnNames().get(0).getText();
        return toIdentifier("uk_" + tableName + "_" + columnName, source.getBuildingContext());
    }

    @Override
    public Identifier determineForeignKeyName(ImplicitForeignKeyNameSource source) {
        String tableName = source.getTableName().getText();
        String referencedTableName = source.getReferencedTableName().getText();
        return toIdentifier("fk_" + tableName + "_" + referencedTableName, source.getBuildingContext());
    }

    @Override
    public Identifier determineIndexName(ImplicitIndexNameSource source) {
        String tableName = source.getTableName().getText();
        String columnName = source.getColumnNames().get(0).getText();
        return toIdentifier("idx_" + tableName + "_" + columnName, source.getBuildingContext());
    }
}
