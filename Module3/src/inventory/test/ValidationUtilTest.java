package inventory.test;

import inventory.exception.InvalidDataException;
import inventory.util.ValidationUtil;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationUtilTest {

    @Test
    public void testValidColor() {

        assertDoesNotThrow(() ->
                ValidationUtil.validateColor("RED"));
    }

    @Test
    public void testInvalidColor() {

        assertThrows(
                InvalidDataException.class,

                () ->
                        ValidationUtil.validateColor("123"));
    }

    @Test
    public void testValidSize() {

        assertDoesNotThrow(() ->
                ValidationUtil.validateSize(10));
    }

    @Test
    public void testInvalidSize() {

        assertThrows(
                InvalidDataException.class,

                () ->
                        ValidationUtil.validateSize(-1));
    }

    @Test
    public void testValidType() {

        assertDoesNotThrow(() ->
                ValidationUtil.validateType("SHOE"));
    }

    @Test
    public void testInvalidType() {

        assertThrows(
                InvalidDataException.class,

                () ->
                        ValidationUtil.validateType("CAR"));
    }
}