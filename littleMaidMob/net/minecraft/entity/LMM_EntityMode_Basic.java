package net.minecraft.entity;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
public class LMM_EntityMode_Basic extends LMM_EntityModeBlockBase {

	public static final int mmode_Wild		= 0x0000;
	public static final int mmode_Escorter	= 0x0001;
	
	private IInventory myInventory;
	private IInventory myChest;
=======
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.LMM_EntityAIAttackOnCollide;
import net.minecraft.src.LMM_EntityAICollectItem;
import net.minecraft.src.LMM_EntityAIHurtByTarget;
import net.minecraft.src.LMM_EnumSound;
import net.minecraft.src.MMM_Helper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.mod_LMM_littleMaidMob;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class LMM_EntityMode_Basic extends LMM_EntityModeBase {

	public static final int mmode_Escorter = 0x0001;
	public static final int mmode_Wild = 0x0000;

>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
	private List<IInventory> fusedTiles;
	private boolean isWorking;
	private double lastdistance;
	private int maidSearchCount;
	private IInventory myChest;
	private IInventory myTile;

	/**
	 * Wild, Escorter
	 */
	public LMM_EntityMode_Basic(LMM_EntityLittleMaid pEntity) {
		super(pEntity);
		fusedTiles = new ArrayList<IInventory>();
//		myTile = null;
	}

	@Override
	public void addEntityMode(EntityAITasks pDefaultMove, EntityAITasks pDefaultTargeting) {
		// Wild
		EntityAITasks[] ltasks = new EntityAITasks[2];
		ltasks[0] = new EntityAITasks(null);
		ltasks[1] = new EntityAITasks(null);

		ltasks[0].addTask(1, owner.aiSwiming);
		ltasks[0].addTask(2, new LMM_EntityAIAttackOnCollide(owner, 0.23F, true));
		ltasks[0].addTask(3, owner.aiPanic);
		ltasks[0].addTask(4, owner.aiBegMove);
		ltasks[0].addTask(4, owner.aiBeg);
		ltasks[0].addTask(5, owner.aiRestrictRain);
		ltasks[0].addTask(6, owner.aiFreeRain);
		//        ltasks[0].addTask(4, new EntityAIMoveIndoors(this));
		//		ltasks[0].addTask(7, owner.aiCloseDoor);
		//		ltasks[0].addTask(8, owner.aiOpenDoor);
		ltasks[0].addTask(9, new LMM_EntityAICollectItem(owner, 0.23F));
		ltasks[0].addTask(10, new EntityAILeapAtTarget(owner, 0.3F));
		ltasks[0].addTask(11, owner.aiWander);
		ltasks[0].addTask(12, new EntityAIWatchClosest2(owner, EntityLiving.class, 10F, 0.02F));
		ltasks[0].addTask(13, new EntityAIWatchClosest2(owner, LMM_EntityLittleMaid.class, 10F, 0.02F));
		ltasks[0].addTask(13, new EntityAIWatchClosest2(owner, EntityPlayer.class, 10F, 0.02F));
		ltasks[0].addTask(13, new EntityAILookIdle(owner));

		ltasks[1].addTask(1, new LMM_EntityAIHurtByTarget(owner, false));

		owner.addMaidMode(ltasks, "Wild", mmode_Wild);

		// Escorter:0x0001
		ltasks = new EntityAITasks[2];
		ltasks[0] = pDefaultMove;
		ltasks[1] = pDefaultTargeting;
		owner.addMaidMode(ltasks, "Escorter", mmode_Escorter);

	}

	@Override
	public boolean changeMode(EntityPlayer pentityplayer) {
		// 強制的に割り当てる
		owner.setMaidMode("Escorter");
		return true;
	}
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
	
	@Override
	public boolean setMode(int pMode) {
		switch (pMode) {
		case mmode_Wild :
			owner.setFreedom(true);
//			owner.aiWander.setEnable(true);
			return true;
		case mmode_Escorter :
			owner.aiAvoidPlayer.setEnable(false);
			for (int li = 0; li < owner.mstatSwingStatus.length; li++) {
				owner.setEquipItem(li, -1);
			}
			return true;
		}
//		owner.getNavigator().clearPathEntity()
		return false;
	}
	
	@Override
	public int getNextEquipItem(int pMode) {
		return pMode == mmode_Wild ? 0 : -1;
	}
	
	@Override
	public boolean checkItemStack(ItemStack pItemStack) {
		return true;
	}

	@Override
	public boolean isSearchBlock() {
		if (owner.getMaidModeInt() == mmode_Escorter && owner.isFreedom() &&
				owner.maidInventory.getFirstEmptyStack() == -1) {
			// �Ώۂ��܂������Ă��Ȃ��Ƃ��͌������s���B
			fDistance = 100F;
			return myInventory == null;
		}
//		clearMy();
//		fusedTiles.clear();
		return false;
	}

	@Override
	public boolean shouldBlock(int pMode) {
		return myInventory instanceof TileEntity;
	}
=======
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java

	@Override
	public boolean checkBlock(int pMode, int px, int py, int pz) {
		TileEntity ltile = owner.worldObj.getBlockTileEntity(px, py, pz);
		if (!(ltile instanceof IInventory)) {
			return false;
		}
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
		if (((IInventory)ltile).getSizeInventory() < 18) {
			// �C���x���g���̃T�C�Y���P�W�ȉ��Ȃ�ΏۂƂ��Ȃ��B
			return false;
		}
		
		// ���E�̃��C�h����
		if (checkWorldMaid(ltile)) return false;
		// �g�p�ς݃`�F�b�N
=======

		// 世界のメイドから
		for (Object lo : owner.worldObj.getLoadedEntityList()) {
			if (lo instanceof LMM_EntityLittleMaid) {
				LMM_EntityLittleMaid lem = (LMM_EntityLittleMaid) lo;
				//				if (lem.isUsingTile(ltile)) {
				//					// 誰かが使っている
				//					return false;
				//				}
			}
		}

>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
		if (fusedTiles.contains(ltile)) {
			// 既に通り過ぎた場所よッ！
			return false;
		}
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
		
		double ldis = owner.getDistanceTilePosSq(ltile);
		if (fDistance > ldis) {
			myInventory = (IInventory)ltile;
			fDistance = ldis;
		}
		return false;
	}

	@Override
	public boolean overlooksBlock(int pMode) {
		// �`�F�X�g�J�[�g�̌���
		List<Entity> list = owner.worldObj.getEntitiesWithinAABB(IInventory.class, owner.boundingBox.expand(8D, 2D, 8D));
		double cartl = 256D;
		for (Entity lentity : list) {
			if (!fusedTiles.contains(lentity)) {
				if (((IInventory)lentity).getSizeInventory() < 18) {
					// �C���x���g�������T�C�Y�ȉ��̓X�L�b�v
					continue;
				}
				double lr = lentity.getDistanceSqToEntity(owner);
				// ������ʒu�ɂ���ł��߂����ׂĂ��Ȃ��J�[�g�`�F�X�g
				
				if (fDistance > lr && owner.getEntitySenses().canSee(lentity)) {
					myInventory = (IInventory)lentity;
					fDistance = lr;
				}
			}
		}
		lastdistance = -1;
		myChest = null;
		maidSearchCount = 0;
		if (myInventory instanceof TileEntity) {
			owner.setTilePos((TileEntity)myInventory);
			return myInventory != null;
		} else {
			owner.setTarget((Entity)myInventory);
			return false;
		}
//		return myInventory != null;
	}

	@Override
	public void startBlock(int pMode) {
	}

	@Override
	public void resetBlock(int pMode) {
		clearMy();
//		fusedTiles.clear();
	}

	protected void clearMy() {
		myInventory = null;
		if (myChest != null) {
			myChest.closeChest();
			myChest = null;
		}
		owner.clearTilePos();
		owner.setTarget(null);
=======
		myTile = (IInventory) ltile;
		return true;
	}

	@Override
	public boolean checkItemStack(ItemStack pItemStack) {
		return true;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
	}

	@Override
	public boolean executeBlock(int pMode, int px, int py, int pz) {
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
//		isMaidChaseWait = true;
		if (myInventory instanceof TileEntityChest) {
			// �u���b�N�n�̃`�F�X�g
			TileEntityChest lchest = (TileEntityChest)myInventory;
			if (!lchest.isInvalid()) {
				// �g�p���O�ɉ�����
=======
		//		isMaidChaseWait = true;
		if (myTile instanceof TileEntityChest) {
			// ブロック系のチェスト
			if (!((TileEntityChest) myTile).isInvalid()) {
				// サーチしたチェスト
				TileEntityChest lchest = (TileEntityChest) myTile;
				// 使用直前に可視判定
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
				if (MMM_Helper.canBlockBeSeen(owner, lchest.xCoord, lchest.yCoord, lchest.zCoord, false, true, false)) {
					if (myChest == null) {
						getChest();
						if (myChest != null) {
							myChest.openChest();
						} else {
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
							// �J���Ȃ��`�F�X�g
							myInventory = null;
=======
							// 開かないチェスト
							myTile = null;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
						}
					}
					// チェストに収納
					owner.setWorking(true);
					putChest();
					return true;
				} else {
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
					// ��������
					clearMy();
				}
			} else {
				// Tile�̏���
				clearMy();
			}
		} else {
			// �z��O�̃C���x���g��
			if (myInventory != null) {
				fusedTiles.add(myInventory);
=======
					// 見失った
					myTile = null;
					if (myChest != null) {
						myChest.closeChest();
						myChest = null;
					}
				}
			} else {
				// Tileの消失
				myTile = null;
				if (myChest != null) {
					myChest.closeChest();
					myChest = null;
				}
			}
		} else if (myTile instanceof Entity) {
			// チェスト付カートとか
			/*
			Entity lentity = (Entity)myTile;
			if (!lentity.isDead) {
				if (owner.getDistanceSqToEntity(lentity) < 5D)	{
			//				if (!hasPath() && getDistanceSqToEntity(myEntity) < 5D)	{
					setPathToEntity(null);
					if (myChest == null) {
						myChest = (EntityMinecart)myEntity;
						serchedChest.add((EntityMinecart)myEntity);
							myChest.openChest();
					}
					if (myChest != null) {
						faceEntity(myEntity, 30F, 40F);
					}
					// チェストに収納
					putChest();
				} else {
					// チェストまでのパスを作る
					if (!isMaidWaitEx()) {
						double distance = getDistanceSqToEntity(myEntity);
						if (distance == lastdistance) {
							mod_littleMaidMob.Debug("Assert.");
							updateWanderPath();
						} else {
							setPathToEntity(worldObj.getPathEntityToEntity(this, myEntity, 16F, true, false, false, true));
						}
						lastdistance = distance;
			//						mod_littleMaidMob.Debug(String.format("Rerute:%b", hasPath()));
						if (myChest != null) {
							myChest.closeChest();
							myChest = null;
						}
					}
				}

			} else {
				// Entityの死亡
				myTile = null;
				if (myChest != null) {
					myChest.closeChest();
					myChest = null;
				}
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
			}
			clearMy();
		}
		return false;
	}

	@Override
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
	public boolean outrangeBlock(int pMode, int pX, int pY, int pZ) {
		// �`�F�X�g�܂ł̃p�X�����
		boolean lf = false;
		if (!owner.isMaidWaitEx()) {
			double distance;
			if (myInventory instanceof TileEntity) {
				distance = owner.getDistanceTilePos();
				if (distance == lastdistance) {
					// TODO:���󖳈Ӗ�
					// �ړ����ł܂�Ȃ��悤�ɗ�������
					mod_LMM_littleMaidMob.Debug("Assert.");
					owner.updateWanderPath();
					lf = true;
				} else {
					lf = MMM_Helper.setPathToTile(owner, (TileEntity)myInventory, false);
				}
			} else {
				distance = 0;
			}
			lastdistance = distance;
			// �����W�O�̃`�F�X�g�͕���
			if (myChest != null) {
				myChest.closeChest();
				myChest = null;
			}
		}
		return lf;
	}

	@Override
=======
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
	public void farrangeBlock() {
		super.farrangeBlock();
		clearMy();
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java

	protected boolean getChest() {
		// �`�F�X�g���l��
		if (myInventory == null) {
			return false;
		}
		// �����ς݂ɃX�^�b�N
		fusedTiles.add(myInventory);
		if (myInventory instanceof TileEntityChest) {
			TileEntityChest lchest = (TileEntityChest)myInventory;
			if (!lchest.adjacentChestChecked) {
				lchest.checkForAdjacentChests();
			}
			fusedTiles.add(lchest.adjacentChestXNeg);
			fusedTiles.add(lchest.adjacentChestXPos);
			fusedTiles.add(lchest.adjacentChestZNeg);
			fusedTiles.add(lchest.adjacentChestZPosition);
		}
		
		TileEntity ltile = (TileEntity)myInventory;
		Block lblock = Block.blocksList[owner.worldObj.getBlockId(ltile.xCoord, ltile.yCoord, ltile.zCoord)];
		myChest = myInventory;
		if (lblock instanceof BlockChest) {
			myChest = ((BlockChest)lblock).getInventory(owner.worldObj, ltile.xCoord, ltile.yCoord, ltile.zCoord);
		}
		
		return myChest != null;
=======
	public boolean getChest() {
		// チェストを獲得
		if (myTile == null) {
			return false;
		}
		World world = owner.worldObj;
		int i = ((TileEntity) myTile).xCoord;
		int j = ((TileEntity) myTile).yCoord;
		int k = ((TileEntity) myTile).zCoord;
		IInventory obj = (TileEntityChest) world.getBlockTileEntity(i, j, k);
		IInventory obj2 = null;
		if (obj == null || world.isRemote) {
			return false;
		}
		// 検索済み
		fusedTiles.add(obj);

		// 開くかどうかの判定
		if (world.isBlockNormalCube(i, j + 1, k)) {
			return false;
		}
		if (world.getBlockId(i - 1, j, k) == Block.chest.blockID && world.isBlockNormalCube(i - 1, j + 1, k)) {
			return false;
		}
		if (world.getBlockId(i + 1, j, k) == Block.chest.blockID && world.isBlockNormalCube(i + 1, j + 1, k)) {
			return false;
		}
		if (world.getBlockId(i, j, k - 1) == Block.chest.blockID && world.isBlockNormalCube(i, j + 1, k - 1)) {
			return false;
		}
		if (world.getBlockId(i, j, k + 1) == Block.chest.blockID && world.isBlockNormalCube(i, j + 1, k + 1)) {
			return false;
		}
		if (world.getBlockId(i - 1, j, k) == Block.chest.blockID) {
			obj2 = (TileEntityChest) world.getBlockTileEntity(i - 1, j, k);
			obj = new InventoryLargeChest("Large chest", obj2, ((obj)));
		}
		if (world.getBlockId(i + 1, j, k) == Block.chest.blockID) {
			obj2 = (TileEntityChest) world.getBlockTileEntity(i + 1, j, k);
			obj = new InventoryLargeChest("Large chest", ((obj)), obj2);
		}
		if (world.getBlockId(i, j, k - 1) == Block.chest.blockID) {
			obj2 = (TileEntityChest) world.getBlockTileEntity(i, j, k - 1);
			obj = new InventoryLargeChest("Large chest", obj2, ((obj)));
		}
		if (world.getBlockId(i, j, k + 1) == Block.chest.blockID) {
			obj2 = (TileEntityChest) world.getBlockTileEntity(i, j, k + 1);
			obj = new InventoryLargeChest("Large chest", ((obj)), obj2);
		}
		if (obj2 != null) {
			fusedTiles.add(obj2);
		}

		myChest = obj;
		return true;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
	}

	@Override
	public int getNextEquipItem(int pMode) {
		return pMode == mmode_Wild ? 0 : -1;
	}

	@Override
	public void init() {
		ModLoader.addLocalization("littleMaidMob.mode.Strike", "Strike");
		ModLoader.addLocalization("littleMaidMob.mode.Wait", "Wait");
		ModLoader.addLocalization("littleMaidMob.mode.Wild", "Wild");
		ModLoader.addLocalization("littleMaidMob.mode.Wild", "ja_JP", "野生種");
		ModLoader.addLocalization("littleMaidMob.mode.Escorter", "Escorter");
		ModLoader.addLocalization("littleMaidMob.mode.Escorter", "ja_JP", "従者");
		ModLoader.addLocalization("littleMaidMob.mode.F-Escorter", "Freedom");
		ModLoader.addLocalization("littleMaidMob.mode.D-Escorter", "D-Escorter");
		ModLoader.addLocalization("littleMaidMob.mode.T-Escorter", "Tracer");
	}

	@Override
	public boolean isSearchBlock() {
		if (owner.getMaidModeInt() == mmode_Escorter && owner.isFreedom()
				&& owner.maidInventory.getFirstEmptyStack() == -1) {
			/*
						// チェストカートの検索
						List<Entity> list = owner.worldObj.getEntitiesWithinAABB(IInventory.class, owner.boundingBox.expand(8D, 2D, 8D));
						double cartl = 256D;
						for (Entity lentity : list) {
							if (!fusedTiles.contains(lentity)) {
								if (lentity instanceof EntityMinecart && ((EntityMinecart)lentity).minecartType != 1) {
									continue;
								}
								double l = lentity.getDistanceSqToEntity(owner);
								// 見える位置にある最も近い調べていないカートチェスト

								if (cartl > l && owner.getEntitySenses().canSee(lentity)) {
									myTile = (IInventory)lentity;
									cartl = l;
								}
							}
						}
			*/
			// Entity系のInventoryが見つからなければ通常のサーチを実行
			return myTile == null;
		}
		return false;
	}

	@Override
	public boolean outrangeBlock(int pMode, int pX, int pY, int pZ) {
		// チェストまでのパスを作る
		boolean lf = false;
		if (!owner.isMaidWaitEx()) {
			double distance;
			if (myTile instanceof TileEntity) {
				distance = ((TileEntity) myTile).getDistanceFrom(owner.posX, owner.posY, owner.posZ);
				if (distance == lastdistance) {
					// 移動が固まらないように乱数加速
					mod_LMM_littleMaidMob.Debug("Assert.");
					owner.updateWanderPath();
					lf = true;
				} else {
					lf = MMM_Helper.setPathToTile(owner, (TileEntity) myTile, false);
				}
			} else {
				distance = 0;
			}
			lastdistance = distance;
			// レンジ外のチェストは閉じる
			if (myChest != null) {
				myChest.closeChest();
				myChest = null;
			}
		}
		return lf;
	}

	@Override
	public int priority() {
		// TODO Auto-generated method stub
		return 9000;
	}

	public void putChest() {
		// チェストに近接
		if (owner.getSwingStatusDominant().canAttack() && myChest != null) {
			// 砂糖、時計、被っているヘルム以外のアイテムを突っ込む
			ItemStack is;
			mod_LMM_littleMaidMob.Debug(String.format("getChest:%d", maidSearchCount));
			while ((is = owner.maidInventory.getStackInSlot(maidSearchCount)) == null
					&& maidSearchCount < owner.maidInventory.mainInventory.length) {
				maidSearchCount++;
			}
			if (is != null && !(
					is.getItem().itemID == Item.sugar.itemID
							|| is.getItem().itemID == Item.pocketSundial.itemID
							|| (is == owner.maidInventory.armorItemInSlot(3))
					//					|| (is.getItem() instanceof ItemArmor && ((ItemArmor)is.getItem()).armorType == 0)
					))
			{
				//				mod_littleMaidMob.Debug("getchest2.");
				boolean f = false;
				for (int j = 0; j < myChest.getSizeInventory() && is.stackSize > 0; j++)
				{
					ItemStack isc = myChest.getStackInSlot(j);
					if (isc == null)
					{
						//						mod_littleMaidMob.Debug(String.format("%s -> NULL", is.getItemName()));
						myChest.setInventorySlotContents(j, is.copy());
						is.stackSize = 0;
						f = true;
						break;
					}
					else if (isc.isStackable() && isc.isItemEqual(is))
					{
						//						mod_littleMaidMob.Debug(String.format("%s -> %s", is.getItemName(), isc.getItemName()));
						f = true;
						isc.stackSize += is.stackSize;
						if (isc.stackSize > isc.getMaxStackSize())
						{
							is.stackSize = isc.stackSize - isc.getMaxStackSize();
							isc.stackSize = isc.getMaxStackSize();
						} else {
							is.stackSize = 0;
							break;
						}
					}
				}
				if (is.stackSize <= 0) {
					owner.maidInventory.setInventorySlotContents(maidSearchCount, null);
				}
				if (f) {
					owner.playSound("random.pop");
					owner.setSwing(2, LMM_EnumSound.Null);
				}
			}
			//			mod_littleMaidMob.Debug(String.format("getchest3:%d", maidSearchCount));
			if (++maidSearchCount >= owner.maidInventory.mainInventory.length) {
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
				// �����ς݂̑Ώۂ��X�^�b�N
//				serchedChest.add(myChest);
				clearMy();
=======
				// 検索済みの対象をスタック
				//				serchedChest.add(myChest);
				myTile = null;
				myChest.closeChest();
				myChest = null;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
				lastdistance = 0D;
				mod_LMM_littleMaidMob.Debug("endChest.");
				// 空きができたら捜索終了
				if (owner.maidInventory.getFirstEmptyStack() > -1) {
					mod_LMM_littleMaidMob.Debug("Search clear.");
					fusedTiles.clear();
				}
			}
		}
	}

	@Override
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
	public boolean attackEntityAsMob(int pMode, Entity pEntity) {
		if (pEntity == myInventory) {
			// �`�F�X�g�t�J�[�g�Ƃ�
			Entity lentity = (Entity)myInventory;
			if (!lentity.isDead) {
				if (owner.getDistanceSqToEntity(lentity) < 5D)	{
					owner.getNavigator().clearPathEntity();
					if (myChest == null) {
						myChest = (IInventory)lentity;
						fusedTiles.add(myChest);
						myChest.openChest();
					}
					if (myChest != null) {
						owner.getLookHelper().setLookPositionWithEntity(lentity, 30F, 40F);
					}
					// �`�F�X�g�Ɏ��[
					putChest();
				} else {
					// �`�F�X�g�܂ł̃p�X�����
					if (!owner.isMaidWaitEx()) {
						double distance = owner.getDistanceSqToEntity(lentity);
						if (distance == lastdistance) {
							// TODO: ���󖳈Ӗ�
							mod_LMM_littleMaidMob.Debug("Assert.");
							owner.updateWanderPath();
						} else {
							owner.getNavigator().tryMoveToXYZ(lentity.posX, lentity.posY, lentity.posZ, owner.getAIMoveSpeed());
						}
						lastdistance = distance;
//						mod_littleMaidMob.Debug(String.format("Rerute:%b", hasPath()));
						if (myChest != null) {
							myChest.closeChest();
							myChest = null;
						}
					}
				}
			} else {
				// Entity�̎��S
				clearMy();
			}
			return true;
		} else {
			// �^�[�Q�b�g���ς���Ă�H
			clearMy();
		}
		return true;
	}

	@Override
	public boolean isChangeTartget(Entity pTarget) {
		if (pTarget instanceof IInventory) {
			return false;
		}
		return super.isChangeTartget(pTarget);
=======
	public void resetBlock(int pMode) {
		myTile = null;
		if (myChest != null) {
			myChest.closeChest();
			myChest = null;
		}
	}

	@Override
	public boolean setMode(int pMode) {
		switch (pMode) {
		case mmode_Wild:
			owner.setFreedom(true);
			//			owner.aiWander.setEnable(true);
			return true;
		case mmode_Escorter:
			owner.aiAvoidPlayer.setEnable(false);
			for (int li = 0; li < owner.mstatSwingStatus.length; li++) {
				owner.setEquipItem(li, -1);
			}
			return true;
		}
		//		owner.getNavigator().clearPathEntity()
		return false;
	}

	@Override
	public boolean shouldBlock(int pMode) {
		return myTile != null;
	}

	@Override
	public void startBlock(int pMode) {
		lastdistance = -1;
		myChest = null;
		maidSearchCount = 0;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
	}

}
