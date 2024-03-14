package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.GroupService;
import RealDolmen.HappyR.model.Group;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Group")
public class GroupController {

    @GetMapping("/all")
    public List<Group> getAllGroups(GroupService groupService) {
        return groupService.getGroupList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getResultById(@PathVariable("id") int groupId, GroupService groupService) {
        Optional<Group> group = groupService.getOptionalGroupById(groupId);
        return group.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Group addGroup(@RequestBody Group newGroup, GroupService groupService){
        return groupService.addGroup(newGroup);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Group> updateGroup(@RequestBody Group updateGroup, @PathVariable("id") int groupId, GroupService groupService){
        Group group = groupService.updateGroupById(updateGroup, groupId);
        if (group==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(group, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteGroup(@PathVariable("id") int groupId, GroupService groupService){
        Optional<Group> group = groupService.getOptionalGroupById(groupId);
        if (group.isPresent()){
            groupService.getGroupList().remove(group.get());
            return new ResponseEntity<>(groupService.getGroupList().size(), HttpStatus.OK);
        }
        return new ResponseEntity<>(groupService.getGroupList().size(), HttpStatus.NOT_FOUND);
    }
}
