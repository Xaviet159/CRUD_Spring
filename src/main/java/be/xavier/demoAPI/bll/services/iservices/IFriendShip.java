package be.xavier.demoAPI.bll.services.iservices;

import java.util.List;

public interface IFriendShip {


    List<IFriendShip> getAll();
    IFriendShip getById(Long id);
    Long create(IFriendShip IFriendShip);
    void update(Long id, IFriendShip IFriendShip);
    void delete(Long id);
}
