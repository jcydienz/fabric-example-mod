package com.example.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    private static final Text WATERMARK_TEXT = Text.literal("MyClient • Fabric");

    @Inject(method = "render", at = @At("TAIL"))
    private void myclient_renderWatermark(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer renderer = client.textRenderer;

        int windowWidth = client.getWindow().getScaledWidth();
        int margin = 6;
        int padding = 4;
        int textWidth = renderer.getWidth(WATERMARK_TEXT);
        int boxWidth = textWidth + padding * 2;
        int boxHeight = renderer.fontHeight + padding * 2;
        int x = windowWidth - boxWidth - margin;
        int y = margin;

        int backgroundColor = 0x70000000;
        int outlineColor = 0x30FFFFFF;

        context.fill(x, y, x + boxWidth, y + boxHeight, backgroundColor);
        context.drawBorder(x, y, boxWidth, boxHeight, outlineColor);
        context.drawText(renderer, WATERMARK_TEXT, x + padding, y + padding, 0xFFFFFF, true);
    }
}