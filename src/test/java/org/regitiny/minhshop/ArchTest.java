package org.regitiny.minhshop;

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
            .importPackages("org.regitiny.minhshop");

        noClasses()
            .that()
            .resideInAnyPackage("org.regitiny.minhshop.service..")
            .or()
            .resideInAnyPackage("org.regitiny.minhshop.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..org.regitiny.minhshop.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
