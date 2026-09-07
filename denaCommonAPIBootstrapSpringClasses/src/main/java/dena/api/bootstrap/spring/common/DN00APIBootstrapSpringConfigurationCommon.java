package dena.api.bootstrap.spring.common;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import dena.api.common.context.DN00SecurityContextProviderMockImpl;
import dena.common.internal.DN00AppCodes;
import jakarta.inject.Provider;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.objectstreamer.MarshallerBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.services.bootstrap.annotations.api.SecurityContextProviderForSystemUser;
import r01f.spring.IsSpringConfiguration;
import r01f.spring.annotation.Prototype;

@Configuration 
public class DN00APIBootstrapSpringConfigurationCommon 
  implements IsSpringConfiguration {
////////////////////////////////////////////////////////////////////////
/// Marshaller
////////////////////////////////////////////////////////////////////////
	@Bean @ModelObjectsMarshaller 
    public Marshaller marshaller() { 	
		return MarshallerBuilder.findTypesToMarshallAtJavaPackages(DN00AppCodes.API_JAVA_PACKAGE)				                            
								.build();
    }    
////////////////////////////////////////////////////////////////////////
///  Security Context Provider
////////////////////////////////////////////////////////////////////////
	/**
	 * [Important! ] Security Context Provider
	 *  ==============================================================
	 *  It should work just to defining a bean this way : 
	 *  
	 * 		 @Bean
			    public Provider<SecurityContext> securityContextProvider(){		
					return new DN00SecurityContextProviderMockImpl();  /// ..a mock impl of security context
				}	
	 *  ...BUT , Spring don´t know  how to create the JSR
	 *  	org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'r01f.securitycontext.SecurityContext' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
			at org.springframework.beans.factory.support.DefaultListableBeanFactory$Jsr330Factory$Jsr330Provider.get(DefaultListableBeanFactory.java:2224)
	 *  TO [SOLVE]
	 *  ----------------------------------------------------------
	 *  [1]  ObjectProvider: 
	 *        []It is a smarter version of Spring's Provider.  It allows Spring to resolve the dependency lazily, avoiding "bean not found" errors during startup.
	 *       
	 *  [2] @Primary: 
	 *        []  With @Primary, you force Spring to use your "bridge" and not any other one lying around on the classpath.
      
        [3] @Scope("prototype"): By being a prototype, every time the loginV2 or the ComboBox request the context, the logic is executed again.
                             
	 * @param context
	 * @return
	 */	
	@Bean 
	@Primary
	public Provider<SecurityContext> securityContextProvider(){		
		return new DN00SecurityContextProviderMockImpl();  
	}	
	@Bean 
	@Lazy	
	@Prototype
	@Primary
	public SecurityContext securityContext(final ObjectProvider<Provider<SecurityContext>> providerObjectProvider) {
		Provider<SecurityContext> objFactory = providerObjectProvider.getObject(); 
		return objFactory.get();
	}	
	
	
////////////////////////////////////////////////////////////////////
/// SYSTEM SECURITY CONTEXT
////////////////////////////////////////////////////////////////////
	@Bean 
	@SecurityContextProviderForSystemUser // 
	public Provider<SecurityContext> systemSecurityContextProvider(){		
		return new DN00SecurityContextProviderMockImpl();  //todo
	}
	
	@Bean 
	@Lazy
	@Scope("prototype") 
	@SecurityContextProviderForSystemUser //
	public SecurityContext systemSecurityContext(final  @SecurityContextProviderForSystemUser    ObjectProvider<Provider<SecurityContext>> providerObjectProvider) {
		Provider<SecurityContext> provider = providerObjectProvider.getObject();
		return provider.get();
	}

	
	
}