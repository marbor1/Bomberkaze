package singletones;

/*Singleton Holder*/
public class MySingletone{
	
	private static class SingletonHolder {
		static MySingletone instance = new MySingletone();
                static int points;
	}
	
	private MySingletone() { 
		System.out.println("game started"); 
                SingletonHolder.points = 0;
	} 
	
	public static MySingletone getInstance() { 
		//System.out.println("Smart Singleton initialized via Holder");
		return SingletonHolder.instance; 
	} 	

	public void activity(int addPoints) {
		SingletonHolder.points += addPoints;
		//System.out.println("Smart Singleton activity");
	}
        public int getPoints()
        {
            return SingletonHolder.points;
        }

}
