
# List of managers by department
SELECT e.name AS manager_name FROM employee e, manager m, department d WHERE m.dept_id = 1 AND m.emp_id = e.id GROUP BY e.id

# List of all managers
SELECT m.id, e.name FROM manager m, employee e WHERE m.emp_id = e.id GROUP BY e.id

# List of reportees by managers
SELECT e.name, e.id FROM employee e WHERE e.id IN (SELECT reportee_id FROM manager WHERE emp_id = 2)

# List of employee details
SELECT id, NAME, ismanager FROM employee WHERE id = 1

SELECT e.name AS manager_name FROM employee e, manager m, department d WHERE m.dept_id = d.id AND m.emp_id = e.id
SELECT e.id AS emp_id, m.id AS mgr_id, e.name FROM manager m, employee e WHERE m.emp_id = e.id
SELECT m.id, e.name FROM manager m, employee e WHERE m.emp_id = e.id GROUP BY e.id
SELECT e.name, m.reportee_id AS reportee_name FROM employee e, manager m WHERE m.emp_id = 2
SELECT reportee_id FROM manager WHERE emp_id = 2
SELECT e.name FROM employee e WHERE e.id IN (SELECT reportee_id FROM manager WHERE emp_id = 2)


