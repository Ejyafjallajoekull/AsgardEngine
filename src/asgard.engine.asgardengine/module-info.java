/**
 * 
 * Defines a 2D engine module.
 * 
 * @author Planters
 */
module asgard.engine.asgardengine {

	exports asgard.engine.asgardengine.game.classes;
	exports asgard.engine.asgardengine.game.classes.actors;
	exports asgard.engine.asgardengine.game.classes.graphics;
	exports asgard.engine.asgardengine.game.classes.items;
	exports asgard.engine.asgardengine.game.classes.scripts;
	exports asgard.engine.asgardengine.game.classes.world;
	exports asgard.engine.asgardengine.game.classes.world.placetree;
	exports asgard.engine.asgardengine.game.entities;
	exports asgard.engine.asgardengine.game.entities.actors;
	exports asgard.engine.asgardengine.game.entities.graphics;
	exports asgard.engine.asgardengine.game.entities.world;
	exports asgard.engine.asgardengine.game.handler;
	exports asgard.engine.asgardengine.main;
	exports asgard.engine.asgardengine.pluginsystem;
	exports asgard.engine.asgardengine.rendering;
	exports asgard.engine.asgardengine.utility;
	exports asgard.engine.asgardengine.utility.binary;
	exports asgard.engine.asgardengine.utility.math;
	exports asgard.engine.asgardengine.utility.quadtree;
	
	requires java.base;
	requires central.logging;
	
}