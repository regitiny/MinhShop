package org.regitiny.tools.magic.utils;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.regitiny.minhshop.security.AuthoritiesConstants;
import org.regitiny.minhshop.security.SecurityUtils;

/**
 * author : @yuvytung
 * this class use to set basic properties before create or update entity
 * use for Entity , EntityDTO
 * example: uuid , role , createdDate , modifiedDate , createdBy , modifiedBy
 */
@Log4j2
public class EntityDefaultPropertiesServiceUtils {

    public static Object setPropertiesBeforeCreate(Object object) {
        log.debug("class name of Object = {}", object.getClass().getName());
        try {
            Instant now = Instant.now();
            String thisUser = null;
            if (SecurityUtils.getCurrentUserLogin().isPresent()) thisUser = SecurityUtils.getCurrentUserLogin().get();
            String createdBy = thisUser;
            String modifiedBy = thisUser;

            object.getClass().getDeclaredMethod("setRole", String.class).invoke(object, AuthoritiesConstants.MANAGEMENT);
            object.getClass().getDeclaredMethod("setUuid", UUID.class).invoke(object, UUID.randomUUID());
            object.getClass().getDeclaredMethod("setCreatedDate", now.getClass()).invoke(object, now);
            object.getClass().getDeclaredMethod("setModifiedDate", now.getClass()).invoke(object, now);
            object.getClass().getDeclaredMethod("setCreatedBy", createdBy.getClass()).invoke(object, createdBy);
            object.getClass().getDeclaredMethod("setModifiedBy", modifiedBy.getClass()).invoke(object, modifiedBy);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static Object setPropertiesBeforeUpdate(Object object) {
        log.debug("class name of Object = {}", object.getClass().getName());
        try {
            Instant now = Instant.now();
            String thisUser = null;
            if (SecurityUtils.getCurrentUserLogin().isPresent()) thisUser = SecurityUtils.getCurrentUserLogin().get();
            String modifiedBy = thisUser;
            object.getClass().getDeclaredMethod("setModifiedDate", now.getClass()).invoke(object, now);
            object.getClass().getDeclaredMethod("setModifiedBy", modifiedBy.getClass()).invoke(object, modifiedBy);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static Object setPropertiesBeforeSave(Object object) {
        try {
            Long id = (Long) object.getClass().getDeclaredMethod("getId").invoke(object);
            log.debug("id = {}", id);
            if (id == null) return setPropertiesBeforeCreate(object); else return setPropertiesBeforeUpdate(object);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return object;
        }
    }
}
