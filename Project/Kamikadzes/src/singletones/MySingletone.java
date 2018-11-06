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
		return SingletonHolder.instance; 
	} 	

	public void activity(int addPoints) {
		SingletonHolder.points += addPoints;
	}
        public int getPoints()
        {
            return SingletonHolder.points;
        }

}
