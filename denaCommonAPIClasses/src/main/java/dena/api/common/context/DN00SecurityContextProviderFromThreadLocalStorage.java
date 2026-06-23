package dena.api.common.context;

import jakarta.inject.Provider;
import r01f.securitycontext.SecurityContext;
import r01f.securitycontext.SecurityContextProviderFromThreadLocalStorageBase;

/**
 * Uses a {@link ThreadLocal}-stored {@link SecurityContext} that usually is put there by
 * a Servlet Filter 
 */
public class DN00SecurityContextProviderFromThreadLocalStorage
     extends SecurityContextProviderFromThreadLocalStorageBase
    implements Provider<SecurityContext> {
/////////////////////////////////////////////////////////////////////////////////////////
// GET
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public SecurityContext get() {
		return super.provideValue();
	}

}
