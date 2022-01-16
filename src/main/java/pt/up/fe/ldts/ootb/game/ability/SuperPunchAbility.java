package pt.up.fe.ldts.ootb.game.ability;

import pt.up.fe.ldts.ootb.game.ability.aoe.TargetAOE;
import pt.up.fe.ldts.ootb.game.ability.effect.CompositeEffect;
import pt.up.fe.ldts.ootb.game.ability.effect.DamageEffect;
import pt.up.fe.ldts.ootb.game.ability.effect.PushEffect;
import pt.up.fe.ldts.ootb.game.ability.range.MeleeRange;

public class SuperPunchAbility extends BaseAbility{
    public SuperPunchAbility() {
        super(new MeleeRange(), new TargetAOE(), new CompositeEffect(new DamageEffect(3), new PushEffect(3)));
    }
}
