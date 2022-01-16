package pt.up.fe.ldts.ootb.game.ability;

import pt.up.fe.ldts.ootb.game.ability.aoe.SideAOE;
import pt.up.fe.ldts.ootb.game.ability.effect.CompositeEffect;
import pt.up.fe.ldts.ootb.game.ability.effect.DamageEffect;
import pt.up.fe.ldts.ootb.game.ability.effect.PushEffect;
import pt.up.fe.ldts.ootb.game.ability.range.MeleeRange;

public class SuperDiveAbility extends BaseAbility {
    public SuperDiveAbility() {
        super(new MeleeRange(), new SideAOE(), new CompositeEffect( new DamageEffect(2), new PushEffect(2)));
    }
}
