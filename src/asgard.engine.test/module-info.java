/**
 * 
 * Defines a testing module for the module asgard.engine.asgardengine.
 * 
 * @author Planters
 */
module asgard.engine.test {

	exports asgard.engine.test;
	exports asgard.engine.test.utility.binary;
	
	requires asgard.engine.asgardengine;
	requires java.base;
	requires koro.sensei.tester;
	
}