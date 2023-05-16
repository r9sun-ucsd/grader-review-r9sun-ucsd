import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

class isB implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("b");
  }
}

public class TestListExamples {

  @Test(timeout = 500)
  public void testFilterMoon() {
    List<String> filterTest = Arrays.asList("bye", "painter", "rob");
    List<String> expected = Arrays.asList();
    assertEquals(expected, ListExamples.filter(filterTest, new isB()));
  }

  @Test(timeout = 500)
  public void testFilterEmpty() {
    List<String> filterTest = Arrays.asList();
    List<String> expected = Arrays.asList();
    assertEquals(expected, ListExamples.filter(filterTest, new isB()));
  }

  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMergeMiddle() {
    List<String> leftRight = Arrays.asList("a", "c");
    List<String> middle = Arrays.asList("b");
    List<String> merged = ListExamples.merge(leftRight, middle);
    List<String> expected = Arrays.asList("a", "b", "c");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMergeMultiple() {
    List<String> left = Arrays.asList("ab", "ac", "ad");
    List<String> right = Arrays.asList("ba", "bc", "bd");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("ab", "ac", "ad", "ba", "bc", "bd");
    assertEquals(expected, merged);
  }

}

