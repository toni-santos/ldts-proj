package pt.up.fe.ldts.ootb.game.ability;

import pt.up.fe.ldts.ootb.game.ability.aoe.TargetAOE;
import pt.up.fe.ldts.ootb.game.ability.effect.DamageEffect;
import pt.up.fe.ldts.ootb.game.ability.range.MeleeRange;

public class DiveAbility extends BaseAbility {
    public DiveAbility() {
        super(new MeleeRange(), new TargetAOE(), new DamageEffect(1));
    }
}
