import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.junit.Assert;

public class RoverMovementSteps {
    private Rover rover;
    private String finalPosition;
    private String finalDirection;

    @Given("^a new Rover on a (\\d+)x(\\d+) grid$")
    public void createNewRoverOnGrid(int gridSizeX, int gridSizeY) {
        rover = new Rover(gridSizeX, gridSizeY);
    }

    @Given("^the Rover is at position \\((\\d+), (\\d+)\\) facing \"([^\"]*)\"$")
    public void setRoverPositionAndDirection(int x, int y, String direction) {
        rover.setPosition(x, y);
        rover.setDirection(direction);
    }

    @When("^I move the Rover according to the commands \"([^\"]*)\"$")
    public void moveRover(String commands) {
        for (char command : commands.toCharArray()) {
            rover.executeCommand(command);
        }
    }

    @Then("^the Rover's final position should be \"([^\"]*)\" and facing \"([^\"]*)\"$")
    public void verifyRoverFinalPositionAndDirection(String expectedPosition, String expectedDirection) {
        finalPosition = rover.getCurrentPosition();
        finalDirection = rover.getCurrentDirection();

        Assert.assertEquals(expectedPosition, finalPosition);
        Assert.assertEquals(expectedDirection, finalDirection);
    }

    @Then("^the Rover's final position and facing should match the expected result$")
    public void verifyRoverPositionAndDirection() {
        Assert.assertEquals(finalPosition, rover.getCurrentPosition());
        Assert.assertEquals(finalDirection, rover.getCurrentDirection());
    }
}
