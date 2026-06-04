package it.uniroma3.diadia.ambienti;

public enum Direzione {
	NORD() {
		@Override
		public Direzione opposta() { return SUD; }
	},
	SUD() {
		@Override
		public Direzione opposta() { return NORD; }
	},
	EST() {
		@Override
		public Direzione opposta() { return OVEST; }
	},
	OVEST() {
		@Override
		public Direzione opposta() { return EST; }
	};

	public abstract Direzione opposta();
}