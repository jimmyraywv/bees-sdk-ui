package org.jimmyray.bees.services;

import org.jimmyray.bees.data.model.BeesCreds;

import com.cloudbees.api.ApplicationListResponse;

public interface BeesAppService {
	
	ApplicationListResponse getAppList(BeesCreds creds) throws Exception;
	
	/*
	Application subcommands:
    app:bind                      Bind a resource to an application
    app:bindings                  List resources bound to an application
    app:cert:validate             Validate a router SSL certificate
    app:create                    Create a new application
    app:delete                    Delete an application
    app:deploy                    Deploy an application
    app:hibernate                 Hibernate an application
    app:info                      Get an application configuration
    app:instance:delete           Delete an application instance
    app:instance:info             Get application instance information
    app:instance:invoke           Invoke an application instance control script
    app:instance:list             List application instances
    app:instance:replace          Re-deploy an application instance
    app:instance:restart          Restart an application instance
    app:instance:set              Set application instance tags
    app:instance:tail             Tail application instance log
    app:invoke                    Invoke a control script on all application instances
    app:list                      List applications
    app:proxy:update              Update an application proxy parameters
    app:resource:create           Create a new application resource
    app:resource:delete           Delete an application resource
    app:resource:list             List application resources
    app:restart                   Restart/redeploy an application
    app:router:create             Create a router resource
    app:router:update             Update a router resource configuration
    app:run                       Run an application locally
    app:scale                     Scale an application up or down
    app:session-store:create      Create a session-store resource
    app:session-store:stats       Get a session-store statistics
    app:snapshot:delete           Delete an application snapshot
    app:snapshot:info             Get application snapshot information
    app:snapshot:list             List application snapshots
    app:snapshot:update           Update application snapshot information
    app:stop                      Stop an application
    app:tail                      Tail the logs of an application
    app:unbind                    Unbind an application resource
    app:update                    Update an application configuration parameters
	 */
}
