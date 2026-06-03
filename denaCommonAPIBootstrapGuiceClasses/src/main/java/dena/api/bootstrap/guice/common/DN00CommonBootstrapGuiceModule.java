package dena.api.bootstrap.guice.common;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;

import dena.api.common.context.DN00SecurityContextProviderForSystemUser;
import dena.common.internal.DN00AppCodes;
import jakarta.inject.Provider;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.objectstreamer.MarshallerBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.services.bootstrap.annotations.api.SecurityContextProviderForSystemUser;

/**
 * Client-API bindings
 */
public class DN00CommonBootstrapGuiceModule
  implements Module {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final Provider<SecurityContext> _securityContextProvider;
	private final Provider<SecurityContext> _systemSecurityContextProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00CommonBootstrapGuiceModule(final Provider<SecurityContext> securityContextProvider) {
		super();
		if (securityContextProvider == null) throw new IllegalArgumentException("The [security context] provider cannot be null!!");
		_securityContextProvider = securityContextProvider;
		_systemSecurityContextProvider = new DN00SecurityContextProviderForSystemUser();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  GUICE MODULE
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void configure(final Binder binder) {
		// Model objects marshaller
		_bindModelObjectsMarshaller(binder);
	}
/////////////////////////////////////////////////////////////////////////////////////////
// 	COMMON BINDINGS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * bindings for the marshaller
	 */
	private static void _bindModelObjectsMarshaller(final Binder binder) {
		// Create the model objects marshaller
		Marshaller marshaller = MarshallerBuilder.findTypesToMarshallAtJavaPackages(DN00AppCodes.API_JAVA_PACKAGE)
												 .build();
		// Bind this instance to the model object's marshaller
		binder.bind(Marshaller.class).annotatedWith(ModelObjectsMarshaller.class)
									 .toInstance(marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	PROVIDERS
/////////////////////////////////////////////////////////////////////////////////////////
	@Provides
	private SecurityContext _provideSecurityContext() {
		return _securityContextProvider.get();
	}
	@Provides @SecurityContextProviderForSystemUser
	private SecurityContext _provideSystemSecurityContext() {
		return _systemSecurityContextProvider.get();
	}
}
