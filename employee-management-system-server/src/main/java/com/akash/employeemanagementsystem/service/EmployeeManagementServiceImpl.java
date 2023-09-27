package com.akash.employeemanagementsystem.service;

import com.akash.employeemanagementsystem.auth_entity.EmployeeUser;
import com.akash.employeemanagementsystem.auth_service.JWTService;
import com.akash.employeemanagementsystem.entity.Branch;
import com.akash.employeemanagementsystem.entity.Employee;
import com.akash.employeemanagementsystem.repository.BranchRepository;
import com.akash.employeemanagementsystem.repository.custom_query_classes.BranchWithManagerQuery;
import com.akash.employeemanagementsystem.repository.custom_query_classes.FindEmployeeBySuperId;
import com.akash.employeemanagementsystem.repository.custom_query_classes.GetEmployeeForDeleteCustomQuery;
import com.akash.employeemanagementsystem.request_payloads.*;
import com.akash.employeemanagementsystem.entity.Gender;
import com.akash.employeemanagementsystem.repository.EmployeeRepository;
import com.akash.employeemanagementsystem.repository.EmployeeUserRepository;
import com.akash.employeemanagementsystem.response_payload.BranchResponsePayload;
import com.akash.employeemanagementsystem.response_payload.EmployeeResponsePayload;
import com.akash.employeemanagementsystem.response_payload.ManagerDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeManagementServiceImpl implements EmployeeService,BranchService{

    private final JWTService jwtService;
    private final EmployeeRepository employeeRepository;
    private final EmployeeUserRepository employeeUserRepository;
    private final BranchRepository branchRepository;
    private final ExternalServices externalServices;
    @Override
    @Transactional
    public EmployeeResponsePayload saveEmployee(EmployeeCreateRequest employeeCreateRequest, String requestHeader){

//        String userName = jwtService.extractUserEmail(requestHeader.substring(7));

        EmployeeUser employeeUser = employeeUserRepository.findEmployeeUserByEmail(requestHeader)
                .orElseThrow(()->new UsernameNotFoundException("Employee user not found"));


        System.out.println(employeeUser.toString());


        Employee employee =Employee.builder()
                .firstName(employeeCreateRequest.getFirstName())
                .employeeUser(employeeUser)
                .branchName(null)
                .lastName(employeeCreateRequest.getLastName())
                .birthDate(externalServices.dateConvertor(employeeCreateRequest.getDateOfBirth()))
                .salary(employeeCreateRequest.getSalary())
                .gender(Gender.MALE)
                .build();

        if(Objects.equals(employeeCreateRequest.getGender(), "female")){
            employee.setGender(Gender.FEMALE);
        } else if (Objects.equals(employeeCreateRequest.getGender(), "other")) {
            employee.setGender(Gender.OTHER);
        }

        employee = employeeRepository.saveAndFlush(employee);

        System.out.println(employee.toString());

         EmployeeResponsePayload employeeResponsePayload =EmployeeResponsePayload.builder()
                 .empId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .gender(employee.getGender())
                .salary(employee.getSalary())
                .clients(null)
                .dateOfBirth(employee.getBirthDate())
//                .branch(null)
                .totalSales((double) 0)
                .build();
        System.out.println(employeeResponsePayload);
    return employeeResponsePayload;
    }

    @Override
    @Transactional
    public EmployeeResponsePayload addEmployeeToBranch(EmployeeAssignToBranch employeeManagerCreateRequest) {

        Employee employeeAdd = employeeRepository.findById(employeeManagerCreateRequest.getEmployeeId())
                .orElseThrow(()->new RuntimeException("Employee not found"));

        BranchWithManagerQuery branchWithManagerQuery = branchRepository.findBranchWithManager(employeeManagerCreateRequest.getBranchId());


        Branch branch =Branch.builder()
                .branchName(branchWithManagerQuery.getBranch_name())
                .branchId(branchWithManagerQuery.getBranch_id())
                .managerStartDate(branchWithManagerQuery.getManager_start_date())
                .build();

        Employee manager = Employee
                .builder()
                .employeeId(branchWithManagerQuery.getEmp_id())
                .lastName(branchWithManagerQuery.getLast_name())
                .firstName(branchWithManagerQuery.getFirst_name())
                .birthDate(branchWithManagerQuery.getBirth_date())
                .gender(branchWithManagerQuery.getGender())
                .salary(branchWithManagerQuery.getSalary())
                .build();

        branch.setEmployee(manager);
//        manager.setBranchName(branch);

        employeeAdd.setBranchName(branch);

        employeeAdd.setEmployee(manager);


        employeeAdd = employeeRepository.saveAndFlush(employeeAdd);

        return EmployeeResponsePayload.builder()
                .empId(employeeAdd.getEmployeeId())
                .gender(employeeAdd.getGender())
                .branch(
                        BranchResponsePayload.builder()
                                .branchId(branch.getBranchId())
                                .managerStartDate(branch.getManagerStartDate())
                                .managerDetails(ManagerDetails.builder()
                                        .managerId(manager.getEmployeeId())
                                        .dateOfBirth(manager.getBirthDate())
                                        .firstName(manager.getFirstName())
                                        .lastName(manager.getLastName())
                                        .gender(manager.getGender())
                                        .build())
                                .branchName(branch.getBranchName())
                                .build()
                )
                .firstName(employeeAdd.getFirstName())
                .lastName(employeeAdd.getLastName())
                .totalSales((double)0)
                .dateOfBirth(employeeAdd.getBirthDate())
                .clients(null)
                .salary(employeeAdd.getSalary())
                .build();
    }

    @Override
    @Transactional
    public EmployeeResponsePayload deleteEmployeeById(String employeeId) {

        EmployeeUser employeeUser = employeeUserRepository.findById(employeeId).orElseThrow(
                ()->new RuntimeException("EmployeeUser not found Exception")
        );

        GetEmployeeForDeleteCustomQuery employee = employeeUserRepository.getEmployeeForDelete(employeeUser.getEmployeeId());

        if(employee==null){
            return null;
        }

        EmployeeResponsePayload employeeResponsePayload = EmployeeResponsePayload
                .builder()
                .empId(employee.getEmp_id())
                .lastName(employee.getLast_name())
                .gender(employee.getGender())
                .firstName(employee.getFirst_name())
                .dateOfBirth(employee.getBirth_date())
                .branch(BranchResponsePayload
                        .builder()
                        .branchId(employee.getBranch_id())
                        .branchName(employee.getBranch_name())
                        .managerStartDate(employee.getManager_start_date())
                        .managerDetails(ManagerDetails
                                .builder()
                                .managerId(employee.getManagerId())
                                .gender(employee.getMangerGender())
                                .firstName(employee.getManagerFirstName())
                                .lastName(employee.getManagerLastName())
                                .dateOfBirth(employee.getManagerBirthDate())
                                .build())
                        .build())
                .salary(employee.getSalary())
                .clients(null)
                .totalSales(null)
                .build();


        employeeUserRepository.delete(employeeUser);


        return employeeResponsePayload;
    }

    @Override
    @Transactional
    public EmployeeResponsePayload changeEmployeeBranch(EmployeeAssignToBranch employeeAssignToBranch) {
//      TODO: check needed
        Employee employee = employeeRepository.findById(employeeAssignToBranch.getEmployeeId()).orElseThrow(
                ()-> new RuntimeException("No Employee found")
        );

        Branch branch = branchRepository.findById(employeeAssignToBranch.getBranchId()).orElseThrow(
                ()-> new RuntimeException("No Employee found")
        );
        BranchWithManagerQuery branchWithManagerQuery =
                branchRepository.findBranchWithManager(employeeAssignToBranch.getBranchId());


        List<FindEmployeeBySuperId> employees = employeeRepository.findAllEmployeesBySuperId(employeeAssignToBranch.getEmployeeId());

        employee.setBranchName(branch);


        employee.setEmployee(Employee
                .builder()
                .employeeId(branchWithManagerQuery.getEmp_id())
                .firstName(branchWithManagerQuery.getFirst_name())
                .lastName(branchWithManagerQuery.getLast_name())
                .salary(branchWithManagerQuery.getSalary())
                .gender(branchWithManagerQuery.getGender())
                .birthDate(branchWithManagerQuery.getBirth_date())
                .branchName(branch)
                .build());
        employeeRepository.save(employee);


        if(employees.isEmpty()){
            List<Employee> employeeList = new ArrayList<>();

            employees.forEach((employee1)->{

                employeeList.add(Employee
                        .builder()
                        .employeeId(employee1.getEmp_id())
                        .firstName(employee1.getFirst_name())
                        .lastName(employee1.getLast_name())
                        .salary(employee1.getSalary())
                        .gender(employee1.getGender())
                        .birthDate(employee1.getBirth_date())
                        .branchName(branch)
                        .employee(null)
                        .build()
                );

            });
            employeeRepository.saveAll(employeeList);
        }


        employeeRepository.save(employee);

        return null;
    }

    @Override
    public EmployeeResponsePayload updateEmployee(EmployeeUpdateRequest employeeUpdateRequest) {
//        TODO
        return null;
    }

    @Override
    public List<EmployeeResponsePayload> getALlEmployees() {
//        TODO
        return null;
    }

    @Override
    public EmployeeResponsePayload geEmployeeByEmployeeId(String empId) {
//        TODO
        return null;
    }

    @Override
    public List<EmployeeResponsePayload> deleteALlEmployee() {
        return null;
    }

    @Override
    @Transactional
    public BranchResponsePayload createBranch(BranchCreateRequest branchCreateRequest) {

        Branch branch = Branch.builder()
                .branchName(branchCreateRequest.getBranchName())
                .build();
        branch = branchRepository.saveAndFlush(branch);

        return BranchResponsePayload.builder()
                .branchId(branch.getBranchId())
                .branchName(branch.getBranchName())
                .managerStartDate(null)
                .managerDetails(null)
                .build();
    }

    @Override
    @Transactional
    public BranchResponsePayload addBranchMangerUpdate(EmployeeBranchUpdateRequest branchUpdateRequest) {

//        If a branch already have a manager then just throw Exception
    try {
        if (branchRepository.isManagerPresent(branchUpdateRequest.getBranchId()) != null) {
            throw new RuntimeException("Manager Already present at the branch");
        }
    }catch (Exception e){
        System.out.println(e.toString());
        return null;
    }


        Branch branch = branchRepository.findById(branchUpdateRequest.getBranchId())
                .orElseThrow(()->new RuntimeException("Branch not found"));

        Employee employeeManager = employeeRepository.findById(branchUpdateRequest.getManagerId())
                .orElseThrow(()->new RuntimeException("Employee not found"));

        branch.setEmployee(employeeManager);
        branch.setManagerStartDate(new Date(System.currentTimeMillis()));


        branch = branchRepository.saveAndFlush(branch);

//        Set the branch id of the Manager.
        employeeManager.setBranchName(branch);
        employeeManager = employeeRepository.saveAndFlush(employeeManager);

        return BranchResponsePayload.builder()
                .branchId(branch.getBranchId())
                .branchName(branch.getBranchName())
                .managerStartDate(new Date(System.currentTimeMillis()))
                .managerDetails(ManagerDetails.builder()
                        .managerId(employeeManager.getEmployeeId())
                        .gender(employeeManager.getGender())
                        .firstName(employeeManager.getFirstName())
                        .lastName(employeeManager.getLastName())
                        .dateOfBirth(employeeManager.getBirthDate())
                        .build())
                .build();

    }

    @Override
    @Transactional
    public BranchResponsePayload changeBranchManager(EmployeeBranchUpdateRequest branchUpdateRequest) {



        Employee employee = employeeRepository.findById(branchUpdateRequest.getManagerId()).orElseThrow(
                ()->new RuntimeException("Employee not found")
        );

        Branch branch = branchRepository.findById(branchUpdateRequest.getBranchId()).orElseThrow(
                ()->new RuntimeException("Branch not found")
        );

        List<Employee> employeeList =
                employeeRepository.findAllEmployeesWhereBranchIdIsThis(branchUpdateRequest.getBranchId());

        branch.setEmployee(employee);
        branch.setManagerStartDate(new Date(System.currentTimeMillis()));

        employeeList.forEach(employee1 -> {
            if(Objects.equals(employee1.getEmployeeId(), employee.getEmployeeId())){
                employee1.setEmployee(null);
            }else{
                employee1.setEmployee(employee);
            }
            employee1.setBranchName(branch);
        });
//        Set the branch of the new Manager
        employee.setBranchName(branch);
        employeeRepository.save(employee);


        employeeRepository.saveAll(employeeList);
        Branch branch1 = branchRepository.saveAndFlush(branch);

        return BranchResponsePayload.builder()
                .branchId(branch1.getBranchId())
                .managerDetails(ManagerDetails
                        .builder()
                        .dateOfBirth(employee.getBirthDate())
                        .managerId(employee.getEmployeeId())
                        .firstName(employee.getFirstName())
                        .lastName(employee.getLastName())
                        .gender(employee.getGender())
                        .build())
                .managerStartDate(branch1.getManagerStartDate())
                .branchName(branch1.getBranchName())
                .build();

    }

    @Override
    public BranchResponsePayload deleteBranchById(String branchId) {
        Branch branch = branchRepository.findById(branchId).orElseThrow(
                ()->new RuntimeException("Branch not found")
        );

        branchRepository.delete(branch);

        return BranchResponsePayload.builder()
                .branchId(branch.getBranchId())
                .managerDetails(null)
                .managerStartDate(branch.getManagerStartDate())
                .branchName(branch.getBranchName())
                .build();

    }

    @Override
    public List<BranchResponsePayload> getAllBranches() {

       List<BranchWithManagerQuery> branches = branchRepository.findAllBranchesWithManager();

       List<BranchResponsePayload> branchResponsePayloads =
               branches.stream().map(branch -> BranchResponsePayload
                       .builder()
                       .branchId(branch.getBranch_id())
                       .branchName(branch.getBranch_name())
                       .managerStartDate(branch.getManager_start_date())
                       .managerDetails(ManagerDetails.builder()
                               .managerId(branch.getEmp_id())
                               .gender(branch.getGender())
                               .firstName(branch.getFirst_name())
                               .lastName(branch.getLast_name())
                               .dateOfBirth(branch.getBirth_date())
                               .build())
                       .build()).toList();

        return branchResponsePayloads;
    }

    @Override
    public BranchResponsePayload getBranchById(String branchId) {

        BranchWithManagerQuery branch = branchRepository.findBranchWithManager(branchId);


        return BranchResponsePayload
                .builder()
                .branchId(branch.getBranch_id())
                .branchName(branch.getBranch_name())
                .managerStartDate(branch.getManager_start_date())
                .managerDetails(ManagerDetails.builder()
                        .managerId(branch.getEmp_id())
                        .gender(branch.getGender())
                        .firstName(branch.getFirst_name())
                        .lastName(branch.getLast_name())
                        .dateOfBirth(branch.getBirth_date())
                        .build())
                .build();
    }

    @Override
    public List<BranchResponsePayload> deleteAllBranches() {

        List<BranchWithManagerQuery> branches = branchRepository.findAllBranchesWithManager();

        branchRepository.deleteAll();


                return branches.stream().map(branch -> BranchResponsePayload
                        .builder()
                        .branchId(branch.getBranch_id())
                        .branchName(branch.getBranch_name())
                        .managerStartDate(branch.getManager_start_date())
                        .managerDetails(ManagerDetails.builder()
                                .managerId(branch.getEmp_id())
                                .gender(branch.getGender())
                                .firstName(branch.getFirst_name())
                                .lastName(branch.getLast_name())
                                .dateOfBirth(branch.getBirth_date())
                                .build())
                        .build()).toList();

    }


}
