package org.jimmyray.bees.services;

import org.jimmyray.bees.data.model.BeesCreds;

import com.cloudbees.api.ServiceListResponse;
import com.cloudbees.api.ServiceSubscriptionListResponse;

public interface BeesServicesService {

	ServiceListResponse getServiceList(BeesCreds creds) throws Exception;
	ServiceSubscriptionListResponse getServiceSubscriptionList (
			BeesCreds creds) throws Exception;
	
/*
 Services subcommands:
    [SERVICE_NAME]:bind           Bind a resource to another
    [SERVICE_NAME]:bindings       List resource bindings
    [SERVICE_NAME]:create         Create a service resource
    [SERVICE_NAME]:delete         Delete a service resource
    [SERVICE_NAME]:info           Get a service resource information
    [SERVICE_NAME]:list           List a service resources
    [SERVICE_NAME]:unbind         Unbind a service resource
    [SERVICE_NAME]:update         Update a service resource
    service:info                  Get a service subscription information
    service:list                  List subscribed services
    service:subscribe             Subscribe to a service
    service:unsubscribe           Un-subscribe from a service
    service:update                Update a service subscription

 */
}
