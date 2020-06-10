package com.digiterra.smartapp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.digiterra.smartapp");

        noClasses()
            .that()
            .resideInAnyPackage("com.digiterra.smartapp.service..")
            .or()
            .resideInAnyPackage("com.digiterra.smartapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.digiterra.smartapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
