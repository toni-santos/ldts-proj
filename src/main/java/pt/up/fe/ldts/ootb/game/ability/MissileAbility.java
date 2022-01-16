package pt.up.fe.ldts.ootb.game.ability;

import pt.up.fe.ldts.ootb.game.ability.aoe.TargetAOE;
import pt.up.fe.ldts.ootb.game.ability.effect.CompositeEffect;
import pt.up.fe.ldts.ootb.game.ability.effect.DamageEffect;
import pt.up.fe.ldts.ootb.game.ability.effect.PushEffect;
import pt.up.fe.ldts.ootb.game.ability.range.CardinalRange;

public class MissileAbility extends BaseAbility{
    public MissileAbility() {
        super(new CardinalRange(1, 4), new TargetAOE(), new CompositeEffect(new DamageEffect(1), new PushEffect(2)));
    }
}
