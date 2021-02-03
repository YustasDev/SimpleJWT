public interface Account {
  public long getMoney();
  public void setMoney(long money);
  public String getAccNumber();
  public void setAccNumber(String accNumber);
  void setLocked(boolean locked);
  boolean isLocked();

}
