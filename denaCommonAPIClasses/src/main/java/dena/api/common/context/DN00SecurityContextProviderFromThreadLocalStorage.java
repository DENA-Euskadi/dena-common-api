package dena.api.common.context;

import jakarta.inject.Provider;
import lombok.extern.slf4j.Slf4j;
import r01f.securitycontext.SecurityContext;
import r01f.securitycontext.SecurityContextProviderFromThreadLocalStorageBase;
import r01f.securitycontext.SecurityContextStoreAtThreadLocalStorage;

/**
 * Uses a {@link ThreadLocal}-stored {@link SecurityContext} that usually is put there by
 * a Servlet Filter 
 */
@Slf4j
public class DN00SecurityContextProviderFromThreadLocalStorage
     extends SecurityContextProviderFromThreadLocalStorageBase
    implements Provider<SecurityContext> {
/////////////////////////////////////////////////////////////////////////////////////////
// GET
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public DN00SecurityContext get() {
		// The [auth context] attached to the ThreadLocal storage at AuthServletFilter
		DN00SecurityContext outSecurityContext = SecurityContextStoreAtThreadLocalStorage.get();
		if (outSecurityContext != null) {
			log.warn("got a [security context] attached to the [thread local] storage for user={}",
					  outSecurityContext.getLoginId());
		} else {
			log.warn("NO [security context] attached to the [thread local] storage: no security filter in use!!");
			throw new IllegalStateException("NO [security context] attached to the [thread local] storage: no security filter in use!!");
		}
		return outSecurityContext;
	}

}
