public enum JobType {
	LAWYER, DOCTOR, DENTIST, ARCHITECT, LIBRARIAN, ENGINEER, PHARMACIST, TEACHER, TRADES;

	public String	toString()
	{
		switch (this) {
			case LAWYER:
				return ("Lawyer");
			case DOCTOR:
				return ("Doctor");
			case DENTIST:
				return ("Dentist");
			case ARCHITECT:
				return ("Architect");
			case LIBRARIAN:
				return ("Librarian");
			case ENGINEER:
				return ("Engineer");
			case PHARMACIST:
				return ("Pharmacist");
			case TEACHER:
				return ("Teacher");
			case TRADES:
				return ("Trades");
		
			default:
				return (null);
		}
	}
}
