package net.mcreator.vlabyss.command;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.common.util.FakePlayerFactory;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.Commands;

import net.mcreator.vlabyss.procedures.SetNbtProcedureProcedure;
import net.mcreator.vlabyss.procedures.SetNbtBlockProcedureProcedure;
import net.mcreator.vlabyss.procedures.RemoveNbtProcedureProcedure;
import net.mcreator.vlabyss.procedures.RemoveNbtBlockProcedureProcedure;

import com.mojang.brigadier.arguments.BoolArgumentType;

@Mod.EventBusSubscriber
public class SetNBTCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("nbt").requires(s -> s.hasPermission(4)).then(Commands.literal("add")
				.then(Commands.literal("item").then(Commands.argument("entity", EntityArgument.player()).then(Commands.argument("value", BoolArgumentType.bool()).then(Commands.argument("nbt", MessageArgument.message()).executes(arguments -> {
					Level world = arguments.getSource().getUnsidedLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null && world instanceof ServerLevel _servLevel)
						entity = FakePlayerFactory.getMinecraft(_servLevel);
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();

					SetNbtProcedureProcedure.execute(arguments);
					return 0;
				}))))).then(Commands.literal("block")
						.then(Commands.argument("blockentity", BlockPosArgument.blockPos()).then(Commands.argument("value", BoolArgumentType.bool()).then(Commands.argument("nbt", MessageArgument.message()).executes(arguments -> {
							Level world = arguments.getSource().getUnsidedLevel();
							double x = arguments.getSource().getPosition().x();
							double y = arguments.getSource().getPosition().y();
							double z = arguments.getSource().getPosition().z();
							Entity entity = arguments.getSource().getEntity();
							if (entity == null && world instanceof ServerLevel _servLevel)
								entity = FakePlayerFactory.getMinecraft(_servLevel);
							Direction direction = Direction.DOWN;
							if (entity != null)
								direction = entity.getDirection();

							SetNbtBlockProcedureProcedure.execute(world, arguments);
							return 0;
						}))))))
				.then(Commands.literal("remove").then(Commands.literal("item").then(Commands.argument("entity", EntityArgument.player()).then(Commands.argument("nbt", MessageArgument.message()).executes(arguments -> {
					Level world = arguments.getSource().getUnsidedLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null && world instanceof ServerLevel _servLevel)
						entity = FakePlayerFactory.getMinecraft(_servLevel);
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();

					RemoveNbtProcedureProcedure.execute();
					return 0;
				})))).then(Commands.literal("block").then(Commands.argument("blockentity", BlockPosArgument.blockPos()).then(Commands.argument("nbt", MessageArgument.message()).executes(arguments -> {
					Level world = arguments.getSource().getUnsidedLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null && world instanceof ServerLevel _servLevel)
						entity = FakePlayerFactory.getMinecraft(_servLevel);
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();

					RemoveNbtBlockProcedureProcedure.execute();
					return 0;
				}))))));
	}

}