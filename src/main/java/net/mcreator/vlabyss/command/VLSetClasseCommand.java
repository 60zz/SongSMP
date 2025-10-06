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
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.Commands;

import net.mcreator.vlabyss.procedures.VesperianClasseProcedure;
import net.mcreator.vlabyss.procedures.ValmirianoClasseProcedure;
import net.mcreator.vlabyss.procedures.SimikariClasseProcedure;
import net.mcreator.vlabyss.procedures.ScribariClasseProcedure;
import net.mcreator.vlabyss.procedures.NimrenClasseProcedure;
import net.mcreator.vlabyss.procedures.LumivivoClasseProcedure;
import net.mcreator.vlabyss.procedures.CapidrelClasseProcedure;
import net.mcreator.vlabyss.procedures.ArkanthiClasseProcedure;

import com.mojang.brigadier.arguments.BoolArgumentType;

@Mod.EventBusSubscriber
public class VLSetClasseCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("setclasse").requires(s -> s.hasPermission(4))
				.then(Commands.literal("Valmiriano").then(Commands.argument("entity", EntityArgument.players()).then(Commands.argument("value", BoolArgumentType.bool()).executes(arguments -> {
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

					ValmirianoClasseProcedure.execute(arguments, entity);
					return 0;
				})))).then(Commands.literal("Arkanthi").then(Commands.argument("entity", EntityArgument.players()).then(Commands.argument("value", BoolArgumentType.bool()).executes(arguments -> {
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

					ArkanthiClasseProcedure.execute(arguments, entity);
					return 0;
				})))).then(Commands.literal("Vesperian").then(Commands.argument("entity", EntityArgument.players()).then(Commands.argument("value", BoolArgumentType.bool()).executes(arguments -> {
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

					VesperianClasseProcedure.execute(arguments, entity);
					return 0;
				})))).then(Commands.literal("Nimren").then(Commands.argument("entity", EntityArgument.players()).then(Commands.argument("value", BoolArgumentType.bool()).executes(arguments -> {
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

					NimrenClasseProcedure.execute(arguments, entity);
					return 0;
				})))).then(Commands.literal("Simikari").then(Commands.argument("entity", EntityArgument.players()).then(Commands.argument("value", BoolArgumentType.bool()).executes(arguments -> {
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

					SimikariClasseProcedure.execute(arguments, entity);
					return 0;
				})))).then(Commands.literal("Scribari").then(Commands.argument("entity", EntityArgument.players()).then(Commands.argument("value", BoolArgumentType.bool()).executes(arguments -> {
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

					ScribariClasseProcedure.execute(arguments, entity);
					return 0;
				})))).then(Commands.literal("Capidrel").then(Commands.argument("entity", EntityArgument.players()).then(Commands.argument("value", BoolArgumentType.bool()).executes(arguments -> {
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

					CapidrelClasseProcedure.execute(arguments, entity);
					return 0;
				})))).then(Commands.literal("Lumivivo").then(Commands.argument("entity", EntityArgument.players()).then(Commands.argument("value", BoolArgumentType.bool()).executes(arguments -> {
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

					LumivivoClasseProcedure.execute(arguments, entity);
					return 0;
				})))));
	}

}