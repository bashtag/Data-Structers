public enum ColorType {
	RESET, BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE;

	public String	getColorCode()
	{
		switch (this) {
			case RESET:
				return ("\033[0m");
			case BLACK:
				return ("\033[40m");
			case RED:
				return ("\033[41m");
			case GREEN:
				return ("\033[42m");
			case YELLOW:
				return ("\033[43m");
			case BLUE:
				return ("\033[44m");
			case MAGENTA:
				return ("\033[45m");
			case CYAN:
				return ("\033[46m");
			case WHITE:
				return ("\033[47m");
		
			default:
				return (null);
		}
	}

	public String	toString()
	{
		switch (this) {
			case RESET:
				return (String.format("Reset\n"));
			case BLACK:
				return (String.format("Black\n"));
			case RED:
				return (String.format("Red\n"));
			case GREEN:
				return (String.format("Green\n"));
			case YELLOW:
				return (String.format("Yellow\n"));
			case BLUE:
				return (String.format("Blue\n"));
			case MAGENTA:
				return (String.format("Magenta\n"));
			case CYAN:
				return (String.format("Cyan\n"));
			case WHITE:
				return (String.format("White\n"));
		
			default:
				return (null);
		}
	}
}
