package aethereal;

import net.minecraft.class_1297;
import net.minecraft.class_1829;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3610;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;

public class NoTrace extends Module {
   private final MultiSelectSetting field0089 = new MultiSelectSetting(
         "notrace.modes",
         new BooleanSetting[]{
            new BooleanSetting("notrace.player", false).method1007("Player").method0210("Hit through entities").method2130("Бить сквозь сущности"),
            new BooleanSetting("notrace.web", true)
               .method1007("Web")
               .method0210("Interact through cobwebs as if invisible")
               .method2130("Взаимодействовать сквозь паутину как если её нет")
         }
      )
      .method1007("Modes")
      .method0210("What to ignore when targeting")
      .method2130("Что игнорировать при наведении");
   private final BooleanSetting field1432 = new BooleanSetting("notrace.nosword", true)
      .method1007("No Sword")
      .method0210("Disable when holding a sword")
      .method2130("Отключить с мечом в руке");

   public NoTrace() {
      super("NoTrace", ModuleCategory.field1470, "Ignores entities or cobwebs when targeting");
      this.method1013("Игнорирует сущности или паутину при наведении");
   }

   private boolean method1755() {
      return this.method2195() && field0796.field_1724 != null
         ? !(field0796.field_1724.method_6047().method_7909() instanceof class_1829) || !this.field1432.method0492()
         : false;
   }

   public boolean method1736() {
      return this.method1755() && this.field0089.method0439("notrace.player").method0492();
   }

   public boolean method1692() {
      return this.method1755() && this.field0089.method0439("notrace.web").method0492();
   }

   public class_3965 method1131(class_1297 var1, double var2, float var4, boolean var5) {
      class_243 var6 = var1.method_5836(var4);
      class_243 var7 = var1.method_5828(var4);
      class_243 var8 = var6.method_1031(var7.field_1352 * var2, var7.field_1351 * var2, var7.field_1350 * var2);
      class_242 var9 = var5 ? class_242.field_1347 : class_242.field_1348;
      class_1937 var10 = field0796.field_1687;
      class_3959 var11 = new class_3959(var6, var8, class_3960.field_17559, var9, var1);
      class_3965[] var12 = new class_3965[1];
      return (class_3965)class_1922.method_17744(
         var6,
         var8,
         var11,
         (ctx, pos) -> {
            class_2680 state = var10.method_8320(pos);
            if (state.method_27852(class_2246.field_10343)) {
               if (var12[0] == null) {
                  class_265 shape = ctx.method_17748(state, var10, pos);
                  class_3965 hit = var10.method_17745(ctx.method_17750(), ctx.method_17747(), pos, shape, state);
                  if (hit != null) {
                     var12[0] = hit;
                  }
               }

               return null;
            } else {
               class_3610 fluid = var10.method_8316(pos);
               class_243 start = ctx.method_17750();
               class_243 end = ctx.method_17747();
               class_265 shape = ctx.method_17748(state, var10, pos);
               class_3965 blockHit = var10.method_17745(start, end, pos, shape, state);
               class_265 fluidShape = ctx.method_17749(fluid, var10, pos);
               class_3965 fluidHit = fluidShape.method_1092(start, end, pos);
               double blockDist = blockHit == null ? 1.7976922776554363E308 : start.method_1025(blockHit.method_17784());
               double fluidDist = fluidHit == null ? 1.7976922776554363E308 : start.method_1025(fluidHit.method_17784());
               return blockDist <= fluidDist ? blockHit : fluidHit;
            }
         },
         ctx -> {
            if (var12[0] != null) {
               return var12[0];
            }

            class_243 dir = ctx.method_17750().method_1020(ctx.method_17747());
            return class_3965.method_17778(
               ctx.method_17747(),
               class_2350.method_10147((float)dir.field_1352, (float)dir.field_1351, (float)dir.field_1350),
               class_2338.method_49638(ctx.method_17747())
            );
         }
      );
   }
}
