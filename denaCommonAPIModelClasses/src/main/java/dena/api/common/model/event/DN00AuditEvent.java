package dena.api.common.model.event;

import com.google.common.collect.Sets;
import r01f.enums.EnumExtended;
import r01f.util.types.collections.CollectionUtils;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DN00AuditEvent
        implements EnumExtended<DN00AuditEvent> {

    /**
     * Login action
     */
     LOGIN,
    /**
     * Login demo action
     */
    LOGIN_DEMO,
    /**
     * Login refresh action
     */
    LOGIN_REFRESH,
    /**
     * Sync device data cache action
     */
    DEVICE_SYNC,
    /**
     * Retrieve data from administration action
     */
    RETRIEVE,
    /**
     * Get Dena registered users action
     */
    GET_USERS,
    /**
     * Sync administration metadata action
     */
    ADMINISTRATION_SYNC,
    /**
     * Passkey init action
     */
    PASSKEY_LOGIN_INIT,
    /*
    * Passkey finish action
    * */
    PASSKEY_LOGIN_FINISH,
    /*
    * Passkey register init action
    * */
    PASSKEY_REGISTER_INIT,
    /*
    * Passkey register finish action
     */
    PASSKEY_REGISTER_FINISH,
    /*
    * Passkey clean credentials action
     */
    PASSKEY_CLEAN_CREDENTIALS;

/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
    public static DN00AuditEvent fromName(final String name) {
        return EnumExtended.fromName(name,DN00AuditEvent.class)
                .orElseThrow(() -> new IllegalArgumentException("no " + DN00AuditEvent.class + " element with name=" + name));
    }
    public static DN00AuditEvent fromNameOrNull(final String name) {
        return EnumExtended.fromName(name,DN00AuditEvent.class)
                .orElse(null);
    }
    public static Set<DN00AuditEvent> fromNames(final String... names) {
        if (CollectionUtils.isNullOrEmpty(names)) return null;
        return Stream.of(names)
                .map(name -> DN00AuditEvent.fromNameOrNull(name))
                .filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableSet());
    }
    public static Set<DN00AuditEvent> asSet() {
        return Sets.newHashSet(DN00AuditEvent.values());
    }

}
