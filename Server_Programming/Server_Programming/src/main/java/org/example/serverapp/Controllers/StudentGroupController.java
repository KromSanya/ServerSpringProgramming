package org.example.serverapp.Controllers;

import org.example.serverapp.Service.GroupService;
import org.example.serverapp.Utils.pojos.CreateGroupPojo;
import org.example.serverapp.entities.StudentGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class StudentGroupController {

    private final GroupService groupService;

    @Autowired
    public StudentGroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<Long> saveGroup(@RequestBody CreateGroupPojo pojo) {
        long groupId = groupService.saveGroup(pojo.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(groupId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentGroup> getGroupById(@PathVariable long id) {
        StudentGroup group = groupService.getGroupById(id);
        return ResponseEntity.ok(group);
    }

    @GetMapping
    public ResponseEntity<List<StudentGroup>> getAllGroups() {
        List<StudentGroup> groups = groupService.getGroups();
        return ResponseEntity.ok(groups);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGroup(@PathVariable long id, @RequestBody String name) {
        groupService.updateGroup(id, name);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupById(@PathVariable long id) {
        groupService.deleteGroupById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllGroups() {
        groupService.deleteGroups();
        return ResponseEntity.noContent().build();
    }
}
