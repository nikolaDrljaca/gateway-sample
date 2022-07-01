package com.example.config;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

public class LoadBalancerConfig {

//    //Change Gateway default load balancing algorithm, default is round robin
//    @Bean
//    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(
//            Environment environment,
//            LoadBalancerClientFactory factory
//    ) {
//        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
//        return new RandomLoadBalancer(factory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
//    }
//
//    @Bean
//    public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
//            ConfigurableApplicationContext context
//    ) {
//        return ServiceInstanceListSupplier.builder()
//                .withDiscoveryClient()
//                .withHealthChecks()
//                .build(context);
//    }

    @Bean
    public ServiceInstanceListSupplier discoveryClientWithHealthChecksServiceInstanceListSupplier(
            ConfigurableApplicationContext context
    ) {
        //new HealthCheckServiceInstanceListSupplier()
        return ServiceInstanceListSupplier.builder()
                .withDiscoveryClient()
                .withHealthChecks()
                .build(context);
    }
}
