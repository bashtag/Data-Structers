public class Street {
	private int	streetLenght;
	private int	numOfRights;
	private int	numOfLefts;
	private IBuilding[]	rightSide;
	private IBuilding[]	leftSide;

	public Street(int streetLenght) {
		this.streetLenght = streetLenght;
	}

	public void	printSilhouette()
	{
		int	i, j, maxHeight = findMaxHeight();
		int	streetMap[] = new int[this.streetLenght];

		for (i = 0; i < this.streetLenght; ++i)
		{
			streetMap[i] = 0;
		}

		for (i = 0; i < this.numOfRights; ++i)
		{
			for (j = this.rightSide[i].getPosition(); j <= this.rightSide[i].getPosition() + this.rightSide[i].getBuildingLength(); ++j)
				streetMap[j] = this.rightSide[i].getHeight();
		}

		for (i = 0; i < this.numOfLefts; ++i)
		{
			for (j = this.leftSide[i].getPosition(); j <= this.leftSide[i].getPosition() + this.leftSide[i].getBuildingLength(); ++j)
				if (streetMap[j] < this.leftSide[i].getHeight())
					streetMap[j] = this.leftSide[i].getHeight();
		}

		for (i = maxHeight; i > 0; --i)
		{
			for (j = 0; j < this.streetLenght; ++j)
			{
				if (i <= streetMap[j])
					System.out.print(ColorType.BLACK.getColorCode());

				System.out.print("  ");
				System.out.print(ColorType.RESET.getColorCode());
			}
			System.out.println();
		}
	}

	/**
	 * Calculation of total remaining length of lands on the street
	 * @param side -> if side is 1, it means right side; 2 means left side
	 * @return
	 */
	public int	calcRemainingLength(boolean side)
	{
		int	totalLength = 0;
		if (side)
			for (int i = 0; i < this.numOfRights; ++i)
				totalLength += this.rightSide[i].getBuildingLength();
		else
			for (int i = 0; i < this.numOfLefts; ++i)
				totalLength += this.leftSide[i].getBuildingLength();
					
		return (this.streetLenght - totalLength);
	}

	/**
	 * 
	 * @return the maximum height of the street
	 */
	private int	findMaxHeight()
	{
		int	max = 0;

		for (int i = 0; i < this.numOfRights; ++i)
			if (this.rightSide[i].getHeight() > max)
				max = this.rightSide[i].getHeight();

		for (int i = 0; i < this.numOfLefts; ++i)
			if (this.leftSide[i].getHeight() > max)
				max = this.leftSide[i].getHeight();

		return (max);
	}

	/**
	 * Remove the building from the street
	 * @param position
	 * @param side -> if side is 1, it means right side; 2 means left side
	 * @return
	 */
	public boolean	removeBuilding(int position, boolean side)
	{
		int	index;
		if (side)
		{
			for (index = 0; index < this.numOfRights; ++index)
				if (this.rightSide[index].getPosition() == position)
					break;

			if (index == this.numOfRights)
				return (false);
			
			for (;index + 1 < this.numOfRights; ++index)
				this.rightSide[index] = this.rightSide[index + 1];
			
			--this.numOfRights;
			return (true);
		}
		else
		{
			for (index = 0; index < this.numOfLefts; ++index)
				if (this.leftSide[index].getPosition() == position)
					break;
	
			if (index == this.numOfLefts)
				return (false);
			
			for (;index + 1 < this.numOfLefts; ++index)
				this.leftSide[index] = this.leftSide[index + 1];
			
			--this.numOfLefts;
			return (true);
		}
	}

	/**
	 * Add a building on the street.
	 * @param building
	 * @param side -> if side is 1, it means right side
	 */
	public boolean	addBuilding(IBuilding building, boolean side)
	{
		// Check
		if (!this.isValidReplacement(building, side))
			return (false);

		if (side)
		{
			if (this.numOfRights % 10 == 0)
				this.realloc(side);
			this.rightSide[this.numOfRights] = building;
			++this.numOfRights;
		}
		else
		{
			if (this.numOfLefts % 10 == 0)
				this.realloc(side);
			this.leftSide[this.numOfLefts] = building;
			++this.numOfLefts;
		}
		return (true);
	}

	/**
	 * If given building is valid for replacement, return true.
	 * Otherwise return false.
	 * @param building
	 * @param side
	 * @return
	 */
	private boolean	isValidReplacement(IBuilding building, boolean side)
	{
		int	position, length, compPos, compLen;

		position = building.getPosition();
		length = building.getBuildingLength();
		/* Compare the buildings on the street and building to be placed. */
		if (side)
		{
			for (int i = 0, j; i < this.numOfRights; ++i)
			{
				compPos = this.rightSide[i].getPosition();
				compLen = this.rightSide[i].getBuildingLength();
				for (j = position; j <= position + length; ++j)
				{
					if (compPos <= j &&
						compPos + compLen >= j)
						return (false);
				}
			}
		}
		else
		{
			for (int i = 0, j; i < this.numOfLefts; ++i)
			{
				compPos = this.leftSide[i].getPosition();
				compLen = this.leftSide[i].getBuildingLength();
				for (j = position; j <= position + length; ++j)
				{
					if (compPos <= j &&
						compPos + compLen >= j)
						return (false);
				}
			}
		}
		return (true);
	}

	/**
	 * reallocation for related side
	 * @param side
	 */
	private void	realloc(boolean side)
	{
		if (side)
		{
			IBuilding[]	newBuilding = new IBuilding[this.numOfRights + 10];

			for (int i = 0; i < this.numOfRights; ++i)
				newBuilding[i] = this.rightSide[i];
			
			this.rightSide = newBuilding;
		}
		else
		{
			IBuilding[]	newBuilding = new IBuilding[this.numOfLefts + 10];

			for (int i = 0; i < this.numOfLefts; ++i)
				newBuilding[i] = this.leftSide[i];
			
			this.leftSide = newBuilding;
		}
	}

	/**
	 * Return the street length
	 * @return
	 */
	public int	getStreetLength()
	{
		return (this.streetLenght);
	}
}
