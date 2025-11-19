package net.mcreator.vlabyss.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class SsmpConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.ConfigValue<Double> TOTALMANTRAWINGEDKNIGHT;
	public static final ForgeConfigSpec.ConfigValue<Double> WINGEDKNIGHTTOTALDAMAGE;
	public static final ForgeConfigSpec.ConfigValue<Double> WINGEDKNIGHTGROUNDAXEATTACKDAMAGE;
	public static final ForgeConfigSpec.ConfigValue<Double> WINGEDKNIGHTFLYINGAXEGROUNDATTACKDAMAGE;
	public static final ForgeConfigSpec.ConfigValue<Double> CAPUZESQUECIDOCONFIG;
	public static final ForgeConfigSpec.ConfigValue<Double> CAPUZESQUECIDOCONFIG2;
	public static final ForgeConfigSpec.ConfigValue<Double> CAPUZESQUECIDOCONFIG3;
	public static final ForgeConfigSpec.ConfigValue<Double> COLARVOTOSILENCIOSOCONFIG;
	public static final ForgeConfigSpec.ConfigValue<Double> AMULETOSANGUECONFIG;
	public static final ForgeConfigSpec.ConfigValue<Double> AMULETOSANGUECONFIG2;
	public static final ForgeConfigSpec.ConfigValue<Double> FAIXAACOLITOCONFIG;
	public static final ForgeConfigSpec.ConfigValue<Double> BOTASVAZIOABYSSALCONFIG;
	public static final ForgeConfigSpec.ConfigValue<Double> BOTASVAZIOABYSSALCONFIG2;
	static {
		BUILDER.push("Winged Knight");
		TOTALMANTRAWINGEDKNIGHT = BUILDER.comment("Vai alterar a mantra total do Cavaleiro Alado").define("Mantra Total que o Cavaleiro Alado vai possuir", (double) 150);
		WINGEDKNIGHTTOTALDAMAGE = BUILDER.comment("Irá alterar o dano total causado pelo Cavaleiro Alado. Ele causará dano verdadeiro").define("Dano base do Cavaleiro Alado", (double) 4);
		WINGEDKNIGHTGROUNDAXEATTACKDAMAGE = BUILDER.comment("Dano causado pelo ataque de acertar o machado no chão do Cavaleiro Alado").define("Dano do Ataque de Machado contra o chão do Cavaleiro Alado", (double) 8);
		WINGEDKNIGHTFLYINGAXEGROUNDATTACKDAMAGE = BUILDER.comment("Dano causado pelo ataque de voar e acertar o ataque no chão do Cavaleiro Alado").define("Dano do Ataque especial do Cavaleiro Alado", (double) 12);
		BUILDER.pop();
		BUILDER.push("Artefatos");
		CAPUZESQUECIDOCONFIG = BUILDER.comment("Adicional de Inteligência por raça. Padrão 2").define("Capuz Esquecido Config", (double) 2);
		CAPUZESQUECIDOCONFIG2 = BUILDER.comment("Perca de vida por artefato equipado. Padrão -2").define("Capuz Esquecido Debuff Config", (double) -2);
		CAPUZESQUECIDOCONFIG3 = BUILDER.comment("Adicional de bônus nas mantras. Padrão: 0.2 (20%)").define("Capuz Esquecido Bonus", (double) 0.2);
		COLARVOTOSILENCIOSOCONFIG = BUILDER.comment("Dano corpo a corpo reduzido. Padrão 1.2 (20%)").define("Voto Silencioso Dano Reduzido", (double) 1.2);
		AMULETOSANGUECONFIG = BUILDER.comment("Vida recuperada pós hit. Padrão (c/ raça): 10").define("Amuleto de Sangue Config", (double) 10);
		AMULETOSANGUECONFIG2 = BUILDER.comment("Vida recuperada pós hit. Padrão (s/ raça): 7").define("Amuleto de Sangue s/raça Config", (double) 7);
		FAIXAACOLITOCONFIG = BUILDER.comment("Inteligência adicional Faixa Acólito. Padrão: 1").define("Artefato Faixa Acólito", (double) 1);
		BOTASVAZIOABYSSALCONFIG = BUILDER.comment("Velocidade das Botas (s/raça): 0.025 (25%)").define("Botas Vazio Abyssal Velocidade (s/raça)", (double) 0.025);
		BOTASVAZIOABYSSALCONFIG2 = BUILDER.comment("Velocidade das Botas (c/raça): 0.04 (40%)").define("Botas Vazio Abyssal Velocidade (c/raça)", (double) 0.03);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}