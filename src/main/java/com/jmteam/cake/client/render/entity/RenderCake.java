package com.jmteam.cake.client.render.entity;

import com.jmteam.cake.common.entity.EntityCake;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.EmptyModelData;

@OnlyIn(Dist.CLIENT)
public class RenderCake extends EntityRenderer<EntityCake> {

    public RenderCake(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(EntityCake entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

        matrixStackIn.push();
        matrixStackIn.translate(-0.5, 0, -0.5);
        BlockState state = Blocks.CAKE.getDefaultState();
        BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
        blockRenderer.renderBlock(state, matrixStackIn, bufferIn, packedLightIn, packedLightIn, EmptyModelData.INSTANCE);
        matrixStackIn.pop();
    }

    @Override
    public ResourceLocation getEntityTexture(EntityCake entity) {
        return new ResourceLocation("");
    }
}
