/*
*
* Useful available asserts:
*   assertEquals("1 should equal 1", 1, 1);
*   assertNotEquals("1 should not equal 2", 1, 2);
*   assertTrue("true should be true", true);
*   assertFalse("false should be false", false);
*   assertNull("null should be null", null);
*   assertNotNull("1 should not be null", 1);
*   assertUndefined("A declared but unassigned variable should have the undefined value", myUndefinedVar);
*   assertNotUndefined("1 should not be undefined", 1);
*   assertNaN("a string should not be a number", "string");
*   assertNotNaN("1 should not be not a number", 1);
*   assertArrayEquals('Equal arrays', [1,2,3], [1,2,3]);
*   assertObjectEquals('Equal objects', {id: 1}, {id: 1});
*
*/

/* example test to check that the header has correct text content */

function testMainHeaderText() {
  let { document } = getTestPage();
  let mainheader = document.querySelector('.mainheader h1');

  assertEquals('Header text should be correct', 'DSP Full Stack Submission', mainheader.textContent);
}

/* example test to check that calling the createTeamsTable method updates the DOM */
/* Note: this will fail because createTeamsTable hasn't yet been implemented in fetchTeams.js */

function testCreateTeamsTable() {
  let { window, document } = getTestPage();
  let tableRowsOnLoad = document.querySelectorAll('table tbody tr');

  assertEquals('No. of rows should be 4', 4, tableRowsOnLoad.length);

  window.createTeamsTable([{name: 'test team'}]);
  let tableRowsUpdated = document.querySelectorAll('table tbody tr');

  assertEquals('No. of rows should be 1', 1, tableRowsUpdated.length);
}

/* continue to add your tests here */
