package com.jappy.mercadopago;

import com.jappy.mercadopago.domain.model.trade.Trade;
import com.jappy.mercadopago.utils.MDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import androidx.room.Room;
import androidx.test.filters.SmallTest;
import androidx.test.runner.AndroidJUnit4;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith (AndroidJUnit4.class)
@SmallTest
public class DaotradeTest {

    MDatabase mMDatabase;

    @Before
    public void initDb() {
        mMDatabase = Room.inMemoryDatabaseBuilder(androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().getTargetContext(),
                MDatabase.class).allowMainThreadQueries().build();
    }

    @After
    public void closeDb() {
        mMDatabase.close();
    }

    @Test
    public void insertTradeData() {
        Trade trade = new Trade("20", 2, "233", "2000", "2018-12-21");
        mMDatabase.mTradeDao().insertAll(trade);
        List<Trade> allTrades = mMDatabase.mTradeDao().getAll().blockingGet();
        assertEquals(allTrades.get(0).getAmount(), trade.getAmount());
    }

    @Test
    public void deleteAll() throws Exception {
        Trade trade = new Trade("20", 2, "233", "2000", "2018-12-21");
        mMDatabase.mTradeDao().insertAll(trade);
        Trade trade1 = new Trade("20", 2, "233", "2000", "2018-12-21");
        mMDatabase.mTradeDao().insertAll(trade1);

        mMDatabase.mTradeDao().delete();
        List<Trade> allTrades = mMDatabase.mTradeDao().getAll().blockingGet();
        assertTrue(allTrades.isEmpty());
    }

    @Test
    public void getAllWords() throws Exception {
        Trade trade = new Trade("20", 2, "233", "2000", "2018-12-21");
        mMDatabase.mTradeDao().insertAll(trade);
        Trade trade1 = new Trade("30", 2, "233", "2000", "2018-12-21");
        mMDatabase.mTradeDao().insertAll(trade1);
        List<Trade> allTrades = mMDatabase.mTradeDao().getAll().blockingGet();
        assertEquals(allTrades.get(0).getAmount(), trade.getAmount());
        assertEquals(allTrades.get(1).getFee(), trade.getFee());
    }
}
