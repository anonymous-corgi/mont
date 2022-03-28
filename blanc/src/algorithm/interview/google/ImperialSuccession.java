package algorithm.interview.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImperialSuccession {
   
  private class Person {
    String name;
    boolean isDead;
    List<Person> children;
    Person(String name) {
      this.name = name;
      this.isDead = false;
      this.children = new ArrayList<>();
    }
    
    String getName() {
      return name;
    }
    
    void die() {
      isDead = true;
    }
    
    boolean isDead() {
      return isDead;
    }
    
    void addChild(Person child) {
      children.add(child);
    }
    
  }
  
  private final Person root;
  private final Map<String, Person> map;
  public ImperialSuccession() {
    this.root = new Person("DadOfKing");
    this.root.die();
    this.map = new HashMap<>();
  }
  
  public void birth(String parent, String name) {
    Person pPerson = map.get(parent);
    Person child = new Person(name);
    pPerson.addChild(child);
    map.put(name, child);
  }
  
  public void death(String name) {
    Person p = map.get(name);
    if (p != null) {
      p.die();
    }
  }
  
  public List<String> getOrderOfSuccession() {
    List<String> res = new ArrayList<>();
    dfs(root, res);
    return res;
  }
  
  private void dfs(Person p, List<String> res) {
    if (p == null) {
      return;
    }
    if (!p.isDead()) {
      res.add(p.getName());
    }
    for (Person child : p.children) {
      dfs(child, res);
    }
  }

}
