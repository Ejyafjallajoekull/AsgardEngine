package asgard.engine.asgardengine.game.classes.scripts;

import asgard.engine.asgardengine.game.entities.graphics.AnimationEntity;

/**
 * The AnimationScript class provides access to all relevant events and functions for AnimationEntitys.
 * 
 * @author Planters
 *
 */
public abstract class AnimationScript extends Script {

	private AnimationEntity source = null;
	
	public AnimationScript(AnimationEntity source) {
		this.setSource(source);
	}
	
	@Override
	public abstract void onInit();
	public abstract void onPlay();
	public abstract void onStop();

	@Override
	public AnimationEntity getSource() {
		return this.source;
	}
	
	public void setSource(AnimationEntity source) {
		this.source = source;
	}
	
}
