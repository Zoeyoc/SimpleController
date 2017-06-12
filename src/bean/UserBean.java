package bean;

public class UserBean {
	private static final String NAME = "zhaoyue";  
    private static final String PASSWORD = "zhaoyue";  
  
    private String username;  
    private String password;  
    private Long id;
    public UserBean() {
		super();
	}
	
    public UserBean(String name, String password) {  
        this.username = name;  
        this.password = password;  
    }  
  
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean checkUser() {  
    	UserDAO dao = new UserDAO();
    	dao.openDBConnection();
    	UserBean userbean = dao.queryUser(this.username);
    	dao.closeDBConnection();
    	if(userbean == null)
    		return false;
    	if(userbean.getPassword().equals(this.password))
    		return true;
    	else 
    		return false;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}  
}
