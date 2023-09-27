select br.branch_id,
       br.branch_name,
       em.emp_id,
       br.manager_start_date,
       em.birth_date,
       em.gender,
       em.first_name,
       em.last_name,
       em.salary
from branch_table br join employee_table em on
    br.manager_id = em.emp_id WHERE br.branch_id = 'd3a6a5f5-8387-4804-8fb6-1dd634255154';


select br.manager_id from branch_table br join employee_table em on br.manager_id = em.emp_id WHERE br
    .branch_id='d3a6a5f5-8387-4804-8fb6-1dd634255154';


# Before delete the Employee from the database
select eu.emp_id,
       et.first_name,
       et.last_name,
       et.gender,
       et.birth_date,
       et.salary,
       br.branch_name,
       br.branch_id,
       br.manager_start_date,
       em.emp_id as managerId,
       em.first_name as managerFirstName,
       em.last_name as managerLastName,
       em.birth_date as managerBirthDate,
       em.gender as managerGender
from employee_user_table eu join
    employee_table et on
    eu.emp_id = et.emp_id join
    branch_table br
    on et
    .branch_id = br.branch_id
    join employee_table em on
    br.manager_id= em.emp_id WHERE eu
    .emp_id='03213100-a072-40dd-82f1-67b8f47f1671';


UPDATE employee_table em join branch_table br on em.branch_id = br.branch_id SET em.super_id='8363fff3-fc3f-4899-a023-97b50f043d47',br.manager_id='8363fff3-fc3f-4899-a023-97b50f043d47'
                                                                             WHERE em.super_id='d462843e-faff-4ddc-8bde-6323c0cd87a6' ;

UPDATE employee_table em SET em.super_id = null WHERE em.emp_id = em.super_id;