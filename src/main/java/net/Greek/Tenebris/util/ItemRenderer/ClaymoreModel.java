//package net.Greek.Tenebris.util.ItemRenderer;
//
//import com.mojang.blaze3d.vertex.PoseStack;
//import net.Greek.Tenebris.block.entity.plushies.BasePlushBlockEntity;
//import net.Greek.Tenebris.item.Tools.Claymore;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.block.BlockRenderDispatcher;
//import net.minecraft.client.renderer.block.ModelBlockRenderer;
//import net.minecraft.client.renderer.block.model.BakedQuad;
//import net.minecraft.client.renderer.block.model.ItemOverrides;
//import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
//import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//import net.minecraft.client.resources.model.BakedModel;
//import net.minecraft.client.resources.model.ModelManager;
//import net.minecraft.core.Direction;
//import net.minecraft.util.RandomSource;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.block.state.BlockState;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.List;
//
//public class ClaymoreModel implements BakedModel {
//
//    private static final ClaymoreModel INSTANCE = new ClaymoreModel();
//
//    private BakedModel model;
//
//    public void setModel(BakedModel model)
//    {
//        this.model = model;
//    }
//
//    @Override
//    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction direction, RandomSource random)
//    {
//        return this.model.getQuads(state, direction, random);
//    }
//
//
//    @Override
//    public boolean useAmbientOcclusion()
//    {
//        return this.model.useAmbientOcclusion();
//    }
//
//    @Override
//    public boolean isGui3d()
//    {
//        return this.model.isGui3d();
//    }
//
//    @Override
//    public boolean usesBlockLight()
//    {
//        return this.model.usesBlockLight();
//    }
//
//    @Override
//    public boolean isCustomRenderer()
//    {
//        return false;
//    }
//
//    @Override
//    public @NotNull TextureAtlasSprite getParticleIcon()
//    {
//        return this.model.getParticleIcon();
//    }
//
//    @Override
//    public @NotNull ItemOverrides getOverrides()
//    {
//        return this.model.getOverrides();
//    }
//
//    @Override
//    public List<RenderType> getRenderTypes(ItemStack itemStack, boolean fabulous)
//    {
//        return null;
//    }
//
//}
