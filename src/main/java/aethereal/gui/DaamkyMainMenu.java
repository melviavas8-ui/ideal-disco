package aethereal;

import java.awt.Color;
import net.minecraft.class_2561;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_429;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_500;
import net.minecraft.class_526;

/**
 * Main menu ported 1:1 from the forum "velocity." menu (same layout, pills, hover animation,
 * gradient + outline), but using Daamky's own render helpers / MSDF fonts.
 * If the custom shaders are not available, it automatically falls back to a vanilla-only drawing
 * of the same layout, so the menu can never be empty.
 */
public class DaamkyMainMenu extends class_437 {
   private static final String LOGO = "daamky.";
   private static final String[] LABELS = new String[]{"Одиночная игра", "Сетевая игра", "Настройки", "Прокси", "Выход"};

   private final float[] hov = new float[5];
   private float s = 1.0F;

   public DaamkyMainMenu() {
      super(class_2561.method_43470("Daamky"));
   }

   protected void method_25426() {
      // shaders can fail at mod init; retry once on the render thread
      if (!ShaderRegistry.isReady()) {
         ShaderRegistry.method0578();
      }
   }

   // renderBackground -> nothing (background is painted in render)
   public void method_25420(class_332 var1, int var2, int var3, float var4) {
   }

   public boolean method_25422() {
      return false;
   }

   // ---- layout (same numbers as the forum menu, multiplied by scale s) ----
   private float[][] layout() {
      float cx = this.field_22789 / 2.0F;
      float bw = 114.0F * this.s;
      float bh = 24.0F * this.s;
      float gap = 5.0F * this.s;
      float row1Y = this.field_22790 / 2.0F - 18.0F * this.s;
      float bh2 = 20.0F * this.s;
      float gap2 = 4.0F * this.s;
      float rowW = 2.0F * bw + gap;
      float bw2 = (rowW - 2.0F * gap2) / 3.0F;
      float row2X = cx - rowW / 2.0F;
      float row2Y = row1Y + bh + gap2;
      return new float[][]{
         {cx - bw - gap / 2.0F, row1Y, bw, bh},
         {cx + gap / 2.0F, row1Y, bw, bh},
         {row2X, row2Y, bw2, bh2},
         {row2X + bw2 + gap2, row2Y, bw2, bh2},
         {row2X + 2.0F * (bw2 + gap2), row2Y, bw2, bh2}
      };
   }

   public void method_25394(class_332 var1, int mouseX, int mouseY, float var4) {
      int w = this.field_22789;
      int h = this.field_22790;
      if (w <= 0 || h <= 0) {
         return;
      }

      ScreenLayoutHelper.method0738(w, h);
      this.s = Math.max(1.0F, ScreenLayoutHelper.method0530());

      this.drawBackground(var1, w, h);

      boolean fancy = ShaderRegistry.isReady();
      if (fancy) {
         Fonts.method0578();
      }

      float[][] b = this.layout();
      if (fancy) {
         this.drawLogoFancy(var1.method_51448(), b[0][1]);
      } else {
         this.drawLogoVanilla(var1, b[0][1]);
      }

      for (int i = 0; i < 5; i++) {
         this.updateHover(i, mouseX, mouseY, b[i]);
         if (fancy) {
            this.pillFancy(var1.method_51448(), i, b[i], LABELS[i], i == 0);
         } else {
            this.pillVanilla(var1, i, b[i], LABELS[i], i == 0);
         }
      }
   }

   private void updateHover(int i, int mx, int my, float[] r) {
      boolean over = mx >= r[0] && mx <= r[0] + r[2] && my >= r[1] && my <= r[1] + r[3];
      this.hov[i] = Math.max(0.0F, Math.min(1.0F, this.hov[i] + (over ? 0.12F : -0.12F)));
   }

   // ---------------------------------------------------------------- background (vanilla fills, always works)
   private void drawBackground(class_332 c, int w, int h) {
      double time = System.currentTimeMillis() / 1000.0;
      float shift = (float)(0.5 + 0.5 * Math.sin(time * 0.6));
      int tr0 = (int)(16 + shift * 10);
      int tg0 = (int)(16 + shift * 10);
      int tb0 = (int)(24 + shift * 14);
      int br0 = 7;
      int bg0 = 7;
      int bb0 = 11;
      for (int y = 0; y < h; y += 2) {
         float t = (float)y / (float)h;
         int r = (int)(tr0 + (br0 - tr0) * t);
         int g = (int)(tg0 + (bg0 - tg0) * t);
         int bl = (int)(tb0 + (bb0 - tb0) * t);
         c.method_25294(0, y, w, Math.min(y + 2, h), 0xFF000000 | (r << 16) | (g << 8) | bl);
      }
   }

   // ---------------------------------------------------------------- fancy (Daamky shaders + MSDF fonts)
   private void drawLogoFancy(class_4587 ms, float row1Y) {
      // montserrat extrabold is not in the jar -> closest bold font available: Inter Bold
      FontSize logo = Fonts.field1633.method0654(44.0F * this.s);
      float lw = logo.method0998(LOGO);
      float lh = logo.method0530();
      float x = (this.field_22789 - lw) / 2.0F;
      float y = row1Y - lh - 12.0F * this.s;
      GuiRenderHelper.method1491(ms, logo, LOGO, x, y, new Color(255, 255, 255, 255));
   }

   private void pillFancy(class_4587 ms, int idx, float[] r, String label, boolean active) {
      float x = r[0];
      float y = r[1];
      float w = r[2];
      float h = r[3];
      float a = this.hov[idx];
      float rad = 5.0F * this.s;
      FontSize font = Fonts.field1258.method0654(8.0F * this.s);
      Color txt;
      if (active) {
         GuiRenderHelper.method1463(ms, x, y, w, h, rad, new Color(242, 242, 246, 255));
         txt = new Color(18, 18, 24, 255);
      } else {
         Color top = new Color(40, 40, 48, 220);
         Color bottom = new Color(24, 24, 31, 220);
         // vertical gradient: vertex order is TL, BL, BR, TR
         RenderCommandFactory.method0548()
            .method0925(new RenderSize(w, h))
            .method0824(new CornerRadius(rad))
            .method0916(new QuadColor(top, bottom, bottom, top))
            .method0555()
            .method1551(ms.method_23760().method_23761(), x, y);
         Color border = new Color(255, 255, 255, (int)(45 + a * 75));
         GuiRenderHelper.method1461(ms, x, y, w, h, rad, 0.5F, 0.5F, border);
         txt = a > 0.5F ? new Color(240, 240, 245, 255) : new Color(160, 160, 172, 235);
      }

      float tw = font.method0998(label);
      float th = font.method0530();
      GuiRenderHelper.method1491(ms, font, label, x + (w - tw) / 2.0F, y + (h - th) / 2.0F, txt);
   }

   // ---------------------------------------------------------------- vanilla fallback (same layout)
   private void drawLogoVanilla(class_332 c, float row1Y) {
      class_327 tr = this.field_22787.field_1772;
      class_2561 logo = class_2561.method_43470(LOGO);
      float scale = 4.0F * this.s;
      float lw = tr.method_27525(logo);
      class_4587 ms = c.method_51448();
      ms.method_22903();
      ms.method_46416(this.field_22789 / 2.0F, row1Y - 9.0F * scale - 12.0F * this.s, 0.0F);
      ms.method_22905(scale, scale, 1.0F);
      c.method_27535(tr, logo, (int)(-lw / 2.0F), 0, 0xFFFFFFFF);
      ms.method_46416(0.35F, 0.0F, 0.0F);
      c.method_27535(tr, logo, (int)(-lw / 2.0F), 0, 0xFFFFFFFF);
      ms.method_22909();
   }

   private void pillVanilla(class_332 c, int idx, float[] r, String label, boolean active) {
      class_327 tr = this.field_22787.field_1772;
      float a = this.hov[idx];
      int ix = Math.round(r[0]);
      int iy = Math.round(r[1]);
      int iw = Math.round(r[2]);
      int ih = Math.round(r[3]);
      int rad = Math.max(2, Math.round(4.0F * this.s));
      int textColor;
      if (active) {
         int v = (int)(242 - a * 14);
         roundRect(c, ix, iy, iw, ih, rad, rgb(v, v, v + 4));
         textColor = rgb(18, 18, 24);
      } else {
         roundRect(c, ix, iy, iw, ih, rad, rgb(lerp(52, 112, a), lerp(52, 112, a), lerp(60, 124, a)));
         roundRect(c, ix + 1, iy + 1, iw - 2, ih - 2, rad - 1, rgb(lerp(34, 46, a), lerp(34, 46, a), lerp(42, 56, a)));
         textColor = a > 0.5F ? rgb(240, 240, 245) : rgb(165, 165, 177);
      }

      class_2561 t = class_2561.method_43470(label);
      int tw = tr.method_27525(t);
      c.method_27535(tr, t, ix + (iw - tw) / 2, iy + (ih - 8) / 2, textColor);
   }

   private static int lerp(int from, int to, float t) {
      return (int)(from + (to - from) * t);
   }

   private static int rgb(int r, int g, int b) {
      return 0xFF000000 | (Math.max(0, Math.min(255, r)) << 16) | (Math.max(0, Math.min(255, g)) << 8) | Math.max(0, Math.min(255, b));
   }

   private static void roundRect(class_332 c, int x, int y, int w, int h, int r, int color) {
      if (w <= 0 || h <= 0) {
         return;
      }
      r = Math.max(1, Math.min(r, Math.min(w, h) / 2));
      for (int i = 0; i < r; i++) {
         double dy = r - i - 0.5;
         int inset = r - (int)Math.round(Math.sqrt(Math.max(0.0, (double)r * r - dy * dy)));
         c.method_25294(x + inset, y + i, x + w - inset, y + i + 1, color);
         c.method_25294(x + inset, y + h - i - 1, x + w - inset, y + h - i, color);
      }
      c.method_25294(x, y + r, x + w, y + h - r, color);
   }

   // ---------------------------------------------------------------- input
   public boolean method_25402(double mx, double my, int button) {
      if (button == 0) {
         float[][] b = this.layout();
         for (int i = 0; i < 5; i++) {
            if (mx >= b[i][0] && mx <= b[i][0] + b[i][2] && my >= b[i][1] && my <= b[i][1] + b[i][3]) {
               this.activate(i);
               return true;
            }
         }
      }

      return super.method_25402(mx, my, button);
   }

   private void activate(int idx) {
      switch (idx) {
         case 0:
            this.field_22787.method_1507(new class_526(this));
            break;
         case 1:
            this.field_22787.method_1507(new class_500(this));
            break;
         case 2:
            this.field_22787.method_1507(new class_429(this, this.field_22787.field_1690));
            break;
         case 3:
            this.field_22787.method_1507(new ProxyScreen(this));
            break;
         default:
            this.field_22787.method_1592();
      }
   }
}
