Simple set of rules for generating OSGI bundles in Bazel. The rules are geared towards sing OSGi as a plugin environment 
in a packaged microservice. 

Rules: 

* `wrapped_osgi_library` create the manifest for an existing jar.
* `osgi_dm_plugin_library` wrap a jar and generate felix dependencymanager descriptors. 


This is a an initial POC more work will be put into it as I begin integrating it into a project. More documentation as 
things develop.