/*	ANGELOS VANAKARIS			p3150006
	KONSTANTINOS KOUMPANAKIS	p3150200	*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class mainApp extends JFrame{
	
	private JButton openD, openO, openS, saveO, saveS, nextDev, nextOrd,
			nextSal, prevDev, prevOrd, prevSal, search, viewDet, viewDet2, viewDet3,
			orderDev, purchaseDev, arrived, searchO, searchS;
	private JLabel resultArea = new JLabel();
	private JLabel resultArea2 = new JLabel();
	private JLabel resultArea3 = new JLabel();
	private JLabel imageViewer, imageViewer2, imageViewer3;
	private JTextArea deviceInfo = new JTextArea();
	private JTextArea orderInfo = new JTextArea();
	private JTextArea saleInfo = new JTextArea();
	private JTextField orderSearch = new JTextField();
	private JTextField saleSearch = new JTextField();
	private JFileChooser fileC;
	JScrollPane listScroller = new JScrollPane(deviceInfo);
	JScrollPane listScroller2 = new JScrollPane(orderInfo);
	JScrollPane listScroller3 = new JScrollPane(saleInfo);
	
	ArrayList <Item> items = new ArrayList<Item>();
	ArrayList<Order> order_list = new ArrayList<Order>();
	ArrayList<Purchased> p_list = new ArrayList<Purchased>();

	String order_date, arrive_date, date, name;
		boolean found;
		int counter = 0, posD = -1, posO = -1, posS = -1, order_code = 0, purchase_code = 0;
		double total_order_price = 0, total_purchase_price = 0;

	String	id = null, model = null, man = null, type = null, 
		res = null, ports = null, format = null, cpu = null,
		gpu = null, sound = null, ec = null;
	int year = 0, quantity = 0, dimensions = 0;
	double	price = 0, megapix = 0, optZoom = 0, digZoom = 0, screenSize = 0,
		hd = 0, fc = 0, rc = 0, cap = 0, bends = 0;
	
	public mainApp(){
		
		setTitle("Main App GUI by 3150006 & 3150200");
		setSize(621, 517);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		getContentPane().add(mainPanel);
		
		JTabbedPane tabPane = new JTabbedPane();
		
		//CREATE THE FIRST TAB
		JPanel firstTab = new JPanel();
		firstTab.setLayout(null);
		
		openD = new JButton("Open...");
		openD.setBounds(0, 0, 110, 30);
		
		prevDev = new JButton("Prev Device");
		prevDev.setBounds(0, 60, 110, 30);
		prevDev.setEnabled(false);
		
		nextDev = new JButton("First Device");
		nextDev.setBounds(110, 60, 110, 30);
		nextDev.setEnabled(false);
		
		search = new JButton("Search");
		search.setBounds(0, 30, 110, 30);
		search.setEnabled(false);
		
		viewDet = new JButton("View more details");
		viewDet.setBounds(330, 90, 280, 30);
		viewDet.setEnabled(false);
		
		orderDev = new JButton("Order Device");
		orderDev.setBounds(330, 400, 140, 59);
		orderDev.setEnabled(false);
		
		purchaseDev = new JButton("Purchase Device");
		purchaseDev.setBounds(470, 400, 140, 59);
		purchaseDev.setEnabled(false);
		
		String[] itemType = {"Picture And Sound", "Gaming", "Home Appliance"};
		JComboBox typeSelect = new JComboBox(itemType);
		typeSelect.setBounds(110, 30, 130, 30);
		typeSelect.setEnabled(false);
		
		String[] categ = {"TV", "BR_DVD", "Camera"};
		JComboBox catSelect = new JComboBox(categ);
		catSelect.setBounds(240, 30, 130, 30);
		catSelect.setEnabled(false);
		
		JTextField modelSearch = new JTextField("Type the model of the device here.");
		modelSearch.setFont(new Font("Serif", Font.ITALIC, 15));
		modelSearch.setForeground(Color.GRAY);
		modelSearch.setBounds(370, 30, 240, 30);
		modelSearch.setEnabled(false);
		
		resultArea.setFont(new Font("Serif", Font.BOLD, 20));
		resultArea.setText("WELCOME!!!");
		resultArea.setHorizontalAlignment(SwingConstants.CENTER);
		resultArea.setBounds(110, 0, 490, 30);
		
		deviceInfo.setFont(new Font("Serif", Font.BOLD, 16));
		deviceInfo.setText("Open a .txt file to load the available devices.\nDevice info will be displayed here.");
		deviceInfo.setEditable(false);
		deviceInfo.setBounds(0, 90, 330, 370);
		listScroller.setBounds(0, 90, 330, 370);
		
		imageViewer = new JLabel(new ImageIcon("Images/PhotoViewer.png"));
		imageViewer.setBounds(330, 120, 280, 280);
		
		fileC = new JFileChooser();
		
		openD.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				File file = null;
				BufferedReader reader = null;
				String line;
				
				int returnVal = fileC.showOpenDialog(mainApp.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					
					//Clear items list
					items.clear();
					
					file = fileC.getSelectedFile();

					try {
						reader = new BufferedReader(new FileReader(file));
					} catch (FileNotFoundException er) {
						resultArea.setText("Error opening file: " + file.getName() + ".");
					}
					
					try {
						counter = 0;
						line = reader.readLine();
						while (line != null){
							if (!line.trim().equals(" ")){
								if (line.trim().equals("ITEM")){
									Item device = new Item();
									line = reader.readLine();
									if (line != null){
										if (line.trim().equals("{")){
											line = reader.readLine();
											while (line != null && !line.trim().equals("}")){
												StringTokenizer st = new StringTokenizer(line);
												String tag = st.nextToken(" ");
												tag = tag.trim().toUpperCase();
												if (tag.equals("ITEM_TYPE")){
													String device_type = st.nextToken(" ").toUpperCase();
													switch(device_type){
					
														case "TV":				device = new TV();				break;
				
														case "BR_DVD":			device = new BR_DVD();			break;
					
														case "CAMERA":			device = new Camera();			break;
					
														case "GAMING":			device = new Gaming();			break;
					
														case "REFRIGERATOR":	device = new Fridge();			break;
					
														case "WASHINGMACHINE":	device = new WashingMachine();	break;
					
													}
												}else{
													String tagInfo = line.trim().substring(tag.length() + 1).trim();
													switch(tag){
				
														case "CODE":			id = tagInfo;								break;
					
														case "MODEL":			model = tagInfo;							break;
				
														case "MODEL_YEAR":		year = Integer.parseInt(tagInfo);			break;
					
														case "MANUFACTURER":	man = tagInfo;								break;
					
														case "PRICE":			price = Double.parseDouble(tagInfo);		break;
					
														case "PIECES":			quantity = Integer.parseInt(tagInfo);		break;
														
														//We have the same variable(type) in these 5 cases.
														case "PANEL_TYPE":
														case "PLAYER_TYPE":
														case "CAMERA_TYPE":
														case "CONSOLE_TYPE":
														case "FRIDGE_TYPE":				type = tagInfo;								break;
														
														case "DIMENSIONS":				dimensions = Integer.parseInt(tagInfo);		break;
														
														case "RESOLUTION":				res = tagInfo;								break;
														
														case "INTERFACES":				ports = tagInfo;							break;
														
														case "FORMAT":					format = tagInfo;							break;
														
														case "MEGAPIXELS":				megapix = Double.parseDouble(tagInfo);		break;
														
														case "OPTICAL_ZOOM":			optZoom = Double.parseDouble(tagInfo);		break;
														
														case "DIGITAL_ZOOM":			digZoom = Double.parseDouble(tagInfo);		break;
														
														case "SCREEN_SIZE":				screenSize = Double.parseDouble(tagInfo);	break;
														
														case "CPU":						cpu = tagInfo;								break;
														
														case "GPU":						gpu = tagInfo;								break;
														
														case "SOUND":					sound = tagInfo;							break;
														
														case "HARD_DISK":				hd = Double.parseDouble(tagInfo);			break;
														
														case "ENERGY_CLASS":			ec = tagInfo;								break;
														
														case "FREEZER_CAPACITY":		fc = Double.parseDouble(tagInfo);			break;
														
														case "REFRIGERATOR_CAPACITY":	rc = Double.parseDouble(tagInfo);			break;
														
														case "CAPACITY":				cap = Double.parseDouble(tagInfo);			break;
														
														case "SPIN_SPEED":				bends = Double.parseDouble(tagInfo);		break;
														
													}

												}
												line = reader.readLine();
											}
											
											device.setID(id);
											device.setModel(model);
											device.setYear(year);
											device.setConstructor(man);
											device.setPrice(price);
											device.setQuantity(quantity);
											
											if(device instanceof PicAndSound){
												
												((PicAndSound)device).setType(type);
												
												if (device instanceof TV){
													
													((TV)device).setResolution(res);
													((TV)device).setDim(dimensions);
													((TV)device).setPorts(ports);
												
												}else if(device instanceof BR_DVD){
													
													((BR_DVD)device).setResolution(res);
													((BR_DVD)device).setFormat(format);
												
												}else{
													
													((Camera)device).setMegapix(megapix);
													((Camera)device).setOpticalZ(optZoom);
													((Camera)device).setDigitalZ(digZoom);
													((Camera)device).setScreenSize(screenSize);
													
												}
											
											}else if(device instanceof Gaming){
												
												((Gaming)device).setType(type);
												((Gaming)device).setCPU(cpu);
												((Gaming)device).setGPU(gpu);
												((Gaming)device).setSound(sound);
												((Gaming)device).setHD(hd);
												
											}else{
												
												((HomeAppliance)device).setEC(ec);
												
												if(device instanceof Fridge){
													
													((Fridge)device).setType(type);
													((Fridge)device).setFreezerCap(fc);
													((Fridge)device).setRefrigeratorCap(rc);
													
												}else{
													
													((WashingMachine)device).setCapacity(cap);
													((WashingMachine)device).setBends(bends);
													
												}
												
											}
											items.add(device);
										}
									}
								}
							}
							line = reader.readLine();
							counter ++;
						}
						
						typeSelect.setEnabled(true);
						catSelect.setEnabled(true);
						modelSearch.setEnabled(true);
						search.setEnabled(true);
						nextDev.setEnabled(true);
						
						deviceInfo.setText(file.getName() + " successfully loaded.");
						
						tabPane.setEnabledAt(1, true);
						tabPane.setEnabledAt(2, true);
						
					}catch (IOException er) {
						resultArea.setText("Error reading line " + counter + ".");
					}
					
				} else {
					resultArea.setText("Open command cancelled.");
				}
			}
		});
		
		nextDev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					orderDev.setEnabled(true);
					purchaseDev.setEnabled(true);
					viewDet.setEnabled(true);
					nextDev.setText("Next Device");
					posD++;
					displayDeviceInfo();
					if(posD > 0){prevDev.setEnabled(true);}
					if(posD >= items.size() - 1){nextDev.setEnabled(false);}
				}catch(IndexOutOfBoundsException ioobe){
					resultArea.setText("Can' t access item list!");
				}
			}
		});
		
		prevDev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					nextDev.setEnabled(true);
					posD--;
					displayDeviceInfo();
					if(posD == 0){prevDev.setEnabled(false);}
				}catch(IndexOutOfBoundsException ioobe){
					resultArea.setText("Can' t access item list!");
				}
			}
		});
		
		typeSelect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox cb = (JComboBox)e.getSource();
				
				String si = (String)cb.getSelectedItem();
				
				if(si.equals("Picture And Sound")){
					
					catSelect.removeAllItems();
					catSelect.addItem("TV");
					catSelect.addItem("BR_DVD");
					catSelect.addItem("Camera");
				
				}else if(si.equals("Gaming")){
					
					catSelect.removeAllItems();
					catSelect.addItem("PS4");
					catSelect.addItem("PS3");
					catSelect.addItem("Xbox");
					catSelect.addItem("Wii");
					
				}else if(si.equals("Home Appliance")){
					
					catSelect.removeAllItems();
					catSelect.addItem("Fridge");
					catSelect.addItem("WashingMachine");
					
				}
				
			}
		});
		
		catSelect.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){}});
		
		modelSearch.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				modelSearch.setText("");
			}
		});
		
		search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				found = false;
				
				if(typeSelect.getSelectedItem().equals("Gaming")){	//catSelect items for Gaming are not Classes but variable "type" in Class "Gaming"!
					for (int i = 0; i < items.size(); i++){
						if (items.get(i) instanceof Gaming){
							if (((Gaming)items.get(i)).getType().equals(catSelect.getSelectedItem())){	//(Gaming): getType() is only a Gaming method. Class Item does not include this method.
								if (items.get(i).getModel().equals(modelSearch.getText())){
									found = true;
									posD = i;
								}
							}
						}
					}
				}else{
					for(int i = 0; i < items.size(); i++){
						try{
							if(Class.forName(catSelect.getSelectedItem().toString()).isInstance(items.get(i))){
								if (items.get(i).getModel().equals(modelSearch.getText())){
									found = true;
									posD = i;
								}
							}
						}catch(ClassNotFoundException cnfe){}
					}
				}
				
				if(found){
					if(posD == items.size() - 1){
						nextDev.setEnabled(false);
						if(items.size() > 1){
							prevDev.setEnabled(true);
						}
					}else{
						if(posD == 0){
							prevDev.setEnabled(false);
							if(items.size() > 1){
								nextDev.setEnabled(true);
							}
						}
					}
					displayDeviceInfo();
				}else{
					if((modelSearch.getText().equals("")) || (modelSearch.getText().equals("Type the model of the device here."))){
						ArrayList<String> results = new ArrayList<String>();
						if(typeSelect.getSelectedItem().equals("Gaming")){	//catSelect items for Gaming are not Classes but variable "type" in Class "Gaming"!
							for (int i = 0; i < items.size(); i++){
								if (items.get(i) instanceof Gaming){
									if (((Gaming)items.get(i)).getType().equals(catSelect.getSelectedItem())){	//(Gaming): getType() is only a Gaming method. Class Item does not include this method.
										Item dev = items.get(i);
										results.add("\nModel: " + dev.getModel() + "\nItem Type: " + dev.getClass().getName() +
										"\nManufacturer: " + dev.getConstructor() + "\nModel Year: " +
										dev.getYear() + "\nAvailable: " + dev.getQuantity() + " pieces\n______________\n");
									}
								}
							}
						}else{
							for(int i = 0; i < items.size(); i++){
								try{
									if(Class.forName(catSelect.getSelectedItem().toString()).isInstance(items.get(i))){
										Item dev = items.get(i);
										results.add("\nModel: " + dev.getModel() + "\nItem Type: " + dev.getClass().getName() +
										"\nManufacturer: " + dev.getConstructor() + "\nModel Year: " +
										dev.getYear() + "\nAvailable: " + dev.getQuantity() + " pieces\n______________\n");
									}
								}catch(ClassNotFoundException cnfe){}
							}
						}
						
						if(results.isEmpty()){
							deviceInfo.setText("There are no items of this category in the list.");
						}else{
							deviceInfo.setText(results.toString());
						}
					}else{
						deviceInfo.setText("Invalid selection.\nDevice with model name\n'" + modelSearch.getText() +
						"'\nnot found.");
					}
				}
			}
		});
		
		viewDet.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				deviceInfo.setText(items.get(posD).toString());
			}
		});
		
		orderDev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				orderDeviceMethod();
				
			}
		});
		
		purchaseDev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (items.get(posD).getQuantity() > 0){
					
					purchaseDeviceMethod(tabPane);
					
				}else{
					
					int dialogResult = JOptionPane.showConfirmDialog(mainApp.this, "This model is no longer available on store.\nDo you want to order it?", "Can' t purchase this device!", JOptionPane.YES_NO_OPTION);
					
					if (dialogResult == JOptionPane.YES_OPTION){
						
						orderDeviceMethod();
						
					}else{
						
						resultArea.setText("The device was not purchased.");
					
					}
				}
			}
		});
		
		firstTab.add(openD);
		firstTab.add(search);
		firstTab.add(prevDev);
		firstTab.add(nextDev);
		firstTab.add(typeSelect);
		firstTab.add(catSelect);
		firstTab.add(resultArea);
		firstTab.add(listScroller);
		firstTab.add(modelSearch);
		firstTab.add(viewDet);
		firstTab.add(imageViewer);
		firstTab.add(orderDev);
		firstTab.add(purchaseDev);
		
		//ADD THE FIRST TAB TO TABBEDPANEL
		tabPane.add("DEVICES", firstTab);
		
		//CREATE THE SECOND TAB
		JPanel secondTab = new JPanel();
		secondTab.setLayout(null);
		
		openO = new JButton("Open...");
		openO.setBounds(0, 0, 110, 30);
		
		saveO = new JButton("Save...");
		saveO.setBounds(0, 30, 110, 30);
		saveO.setEnabled(false);
		
		prevOrd = new JButton("Prev Order");
		prevOrd.setBounds(0, 60, 110, 30);
		prevOrd.setEnabled(false);
		
		nextOrd = new JButton("First Order");
		nextOrd.setBounds(110, 60, 110, 30);
		nextOrd.setEnabled(false);
		
		viewDet2 = new JButton("View more details");
		viewDet2.setBounds(330, 90, 280, 30);
		viewDet2.setEnabled(false);
		
		arrived = new JButton("This device has arrived");
		arrived.setBounds(330, 400, 280, 59);
		arrived.setEnabled(false);
		
		searchO = new JButton("Search");
		searchO.setBounds(240, 30, 130, 30);
		searchO.setEnabled(false);
		
		orderSearch = new JTextField("Type the name of the order here.");
		orderSearch.setFont(new Font("Serif", Font.ITALIC, 15));
		orderSearch.setForeground(Color.GRAY);
		orderSearch.setBounds(370, 30, 240, 30);
		orderSearch.setEnabled(false);
		
		resultArea2.setFont(new Font("Serif", Font.BOLD, 20));
		resultArea2.setHorizontalAlignment(SwingConstants.CENTER);
		resultArea2.setBounds(110, 0, 490, 30);
		
		orderInfo.setFont(new Font("Serif", Font.BOLD, 16));
		orderInfo.setText("Open a .txt file to load the ordered devices.\nOrder info will be displayed here.");
		orderInfo.setEditable(false);
		orderInfo.setBounds(0, 90, 330, 370);
		listScroller2.setBounds(0, 90, 330, 370);
		
		imageViewer2 = new JLabel(new ImageIcon("Images/PhotoViewer.png"));
		imageViewer2.setBounds(330, 120, 280, 280);
		
		openO.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				File file = null;
				BufferedReader reader = null;
				String line;
				
				int returnVal = fileC.showOpenDialog(mainApp.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					
					//Clear order_list
					order_list.clear();
					
					file = fileC.getSelectedFile();
					
					try {
						reader = new BufferedReader(new FileReader(file));
					} catch (FileNotFoundException er) {
						resultArea2.setText("Error opening file: " + file.getName() + ".");
					}
					
					//This "try" creates the order_list ArrayList.
					try {
						counter = 0;
						line = reader.readLine();
						while (line != null){
							if (!line.trim().equals(" ")){
								if (line.trim().equals("ORDER")){
									Item device = new Item();
									Order order = new Order();
									line = reader.readLine();
									if (line != null){
										if (line.trim().equals("{")){
											line = reader.readLine();
											while (line != null && !line.trim().equals("}")){
												StringTokenizer st = new StringTokenizer(line);
												String tag = st.nextToken(" ");
												tag = tag.trim().toUpperCase();
												if (tag.equals("MODEL")){
													String device_model = st.nextToken(" ").toUpperCase();
													for(int i = 0; i < items.size(); i++){
														if(items.get(i).getModel().equals(device_model)){
															order.setDevice(items.get(i));
														}
													}
												}else{
													String tagInfo = line.trim().substring(tag.length() + 1).trim();
													switch(tag){
														
														case "ORDER_NUMBER":	order.setOrderCode(Integer.parseInt(tagInfo));			break;
														
														case "NAME":			order.setName(tagInfo);									break;
														
														case "ORDER_DATE":		order.setOrderDate(tagInfo);							break;
														
														case "DELIVERY_DATE":	order.setArriveDate(tagInfo);							break;
														
														case "PHONE":			order.setPhone(tagInfo);								break;
														
														case "STATUS":			if(tagInfo.equals("ARRIVED")){order.setArrived(true);}	break;
														
													}
												}
												line = reader.readLine();
											}
										}
									}
									order_list.add(order);
								}
							}
							line = reader.readLine();
							if(line != null){
								StringTokenizer st = new StringTokenizer(line);
								String tag = st.nextToken(" ");
								tag = tag.trim().toUpperCase();
								if (tag.equals("TOTAL_ORDER_PRICE")){
									total_order_price = Double.parseDouble(st.nextToken(" "));
								}
							}
							counter ++;
						}
						order_code = order_list.size();
						posO = -1;
						saveO.setEnabled(true);
						nextOrd.setEnabled(true);
						searchO.setEnabled(true);
						orderSearch.setEnabled(true);
						nextOrd.setText("First Order");
					
						orderInfo.setText(file.getName() + " successfully loaded.");
						resultArea2.setText("Total order price: " + total_order_price);
						
					}catch (IOException er) {
						resultArea2.setText("Error reading line " + counter + ".");
					}
					
				} else {
					resultArea2.setText("Open command cancelled.");
				}
			}
		});
		
		saveO.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				int returnVal = fileC.showSaveDialog(mainApp.this);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					
					//Open or create ORDER_LIST.txt.
					try {
						File f_order = fileC.getSelectedFile();

						FileWriter fw = new FileWriter(f_order.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write("ORDER_LIST");
						bw.newLine();
						bw.write("{");
						bw.newLine();
						bw.write("\t");
						bw.newLine();
						for(int i = 0; i < order_list.size(); i++){
							
							write(order_list.get(i), bw);
						
						}
						
						bw.write("\tTOTAL_ORDER_PRICE " + total_order_price);
						bw.newLine();
						bw.write("\t");
						bw.newLine();
						bw.write("}");
						bw.close();
					
					}catch(IOException er) {
						er.printStackTrace();
					}
					
				}else{
					resultArea2.setText("Save command cancelled.");
				}
				
			}
		});
		
		nextOrd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					viewDet2.setEnabled(true);
					arrived.setEnabled(true);
					nextOrd.setText("Next Order");
					resultArea2.setText("Total order price: " + total_order_price);
					posO++;
					displayOrderInfo();
					if(posO > 0){prevOrd.setEnabled(true);}
					if(posO >= order_list.size() - 1){nextOrd.setEnabled(false);}
				}catch(IndexOutOfBoundsException ioobe){
					resultArea2.setText("Can' t access order list!");
				}
			}
		});
		
		prevOrd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					nextOrd.setEnabled(true);
					resultArea2.setText("Total order price: " + total_order_price);
					posO--;
					displayOrderInfo();
					if(posO == 0){prevOrd.setEnabled(false);}
				}catch(IndexOutOfBoundsException ioobe){
					resultArea2.setText("Can' t access order list!");
				}
			}
		});
		
		viewDet2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				orderInfo.setText(order_list.get(posO).toString());
			}
		});
		
		arrived.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				order_list.get(posO).setArrived(true);
				order_list.get(posO).getDevice().setQuantity(order_list.get(posO).getDevice().getQuantity() + 1);
				
				int dialogResult = JOptionPane.showConfirmDialog(mainApp.this, "Success! Device with order code: " + order_list.get(posO).getOrderCode() + " has arrived.\nDo you want to purchase this model?", "Device arrived!", JOptionPane.YES_NO_OPTION);
				
				if (dialogResult == JOptionPane.YES_OPTION){
					
					purchaseDeviceMethod(tabPane);
					
				}else{
					
					resultArea2.setText("The device was not purchased.");
					displayOrderInfo();
					
				}
				
			}
		});
		
		orderSearch.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				orderSearch.setText("");
			}
		});
		
		searchO.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				found = false;
				
				if(!((orderSearch.getText().equals("")) || (orderSearch.getText().equals("Type the name of the order here.")))){
					for(int i = 0; i < order_list.size(); i++){
						if(order_list.get(i).getName().equals(orderSearch.getText())){
							found = true;
							posO = i;
						}
					}
				}
				
				if(found){
					if(posO == order_list.size() - 1){	//Last item.
						nextOrd.setEnabled(false);
						if(order_list.size() > 1){
							prevOrd.setEnabled(true);
						}
					}else{
						if(posO == 0){
							prevOrd.setEnabled(false);
							if(order_list.size() > 1){
								nextOrd.setEnabled(true);
							}
						}
					}
					displayOrderInfo();
				}else{
					orderInfo.setText("Invalid selection.\nDevice with name\n'" + orderSearch.getText() +
					"'\nnot found.");
				}
			}
		});
		
		secondTab.add(openO);
		secondTab.add(saveO);
		secondTab.add(nextOrd);
		secondTab.add(prevOrd);
		secondTab.add(viewDet2);
		secondTab.add(arrived);
		secondTab.add(resultArea2);
		secondTab.add(listScroller2);
		secondTab.add(imageViewer2);
		secondTab.add(searchO);
		secondTab.add(orderSearch);
		
		//ADD THE SECOND TAB TO TABBEDPANEL
		tabPane.add("ORDERS", secondTab);
		tabPane.setEnabledAt(1, false);
		
		//CREATE THE THIRD TAB
		JPanel thirdTab = new JPanel();
		thirdTab.setLayout(null);
		
		openS = new JButton("Open...");
		openS.setBounds(0, 0, 110, 30);
		
		saveS = new JButton("Save...");
		saveS.setBounds(0, 30, 110, 30);
		saveS.setEnabled(false);
		
		prevSal = new JButton("Prev Sale");
		prevSal.setBounds(0, 60, 110, 30);
		prevSal.setEnabled(false);
		
		nextSal = new JButton("First Sale");
		nextSal.setBounds(110, 60, 110, 30);
		nextSal.setEnabled(false);
		
		viewDet3 = new JButton("View more details");
		viewDet3.setBounds(330, 90, 280, 30);
		viewDet3.setEnabled(false);
		
		searchS = new JButton("Search");
		searchS.setBounds(240, 30, 130, 30);
		searchS.setEnabled(false);
		
		saleSearch = new JTextField("Type the name of the sale here.");
		saleSearch.setFont(new Font("Serif", Font.ITALIC, 15));
		saleSearch.setForeground(Color.GRAY);
		saleSearch.setBounds(370, 30, 240, 30);
		saleSearch.setEnabled(false);
		
		resultArea3.setFont(new Font("Serif", Font.BOLD, 20));
		resultArea3.setHorizontalAlignment(SwingConstants.CENTER);
		resultArea3.setBounds(110, 0, 490, 30);
		
		saleInfo.setFont(new Font("Serif", Font.BOLD, 16));
		saleInfo.setText("Open a .txt file to load the purchased devices.\nSale info will be displayed here.");
		saleInfo.setEditable(false);
		saleInfo.setBounds(0, 90, 330, 370);
		listScroller3.setBounds(0, 90, 330, 370);
		
		imageViewer3 = new JLabel(new ImageIcon("Images/PhotoViewer.png"));
		imageViewer3.setBounds(330, 120, 280, 280);
		
		openS.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				File file = null;
				BufferedReader reader = null;
				String line;
				
				int returnVal = fileC.showOpenDialog(mainApp.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					
					//Clear p_list
					p_list.clear();
					
					file = fileC.getSelectedFile();

					try {
						reader = new BufferedReader(new FileReader(file));
					} catch (FileNotFoundException er) {
						resultArea3.setText("Error opening file: " + file.getName() + ".");
					}
					
					//This "try" creates the p_list ArrayList.
					try {
						counter = 0;
						line = reader.readLine();
						while (line != null){
							if (!line.trim().equals(" ")){
								if (line.trim().equals("SALE")){
									Item device = new Item();
									Purchased p = new Purchased();
									line = reader.readLine();
									if (line != null){
										if (line.trim().equals("{")){
											line = reader.readLine();
											while (line != null && !line.trim().equals("}")){
												StringTokenizer st = new StringTokenizer(line);
												String tag = st.nextToken(" ");
												tag = tag.trim().toUpperCase();
												if (tag.equals("MODEL")){
													String device_model = st.nextToken(" ").toUpperCase();
													for(int i = 0; i < items.size(); i++){
														if(items.get(i).getModel().equals(device_model)){
															p.setDevice(items.get(i));
														}
													}
												}else{
													String tagInfo = line.trim().substring(tag.length() + 1).trim();
													switch(tag){
														
														case "SALE_NUMBER":	p.setPurchaseCode(Integer.parseInt(tagInfo));	break;
														
														case "NAME":		p.setName(tagInfo);								break;
														
														case "SALE_DATE":	p.setDate(tagInfo);								break;
														
														case "PHONE":		p.setPhone(tagInfo);							break;
														
													}
												}
												line = reader.readLine();
											}
										}
									}
									p_list.add(p);
								}
							}
							line = reader.readLine();
							if(line != null){
								StringTokenizer st = new StringTokenizer(line);
								String tag = st.nextToken(" ");
								tag = tag.trim().toUpperCase();
								if (tag.equals("TOTAL_SALES_PRICE")){
									total_purchase_price = Double.parseDouble(st.nextToken(" "));
								}
							}
							counter ++;
						}
						purchase_code = p_list.size();
						posS = -1;
						saveS.setEnabled(true);
						nextSal.setEnabled(true);
						searchS.setEnabled(true);
						saleSearch.setEnabled(true);
						nextSal.setText("First Sale");
						
						saleInfo.setText(file.getName() + " successfully loaded.");
						resultArea3.setText("Total purchase price: " + total_purchase_price);
						
					}catch (IOException er) {
						resultArea3.setText("Error reading line " + counter + ".");
					}
					
				} else {
					resultArea3.setText("Open command cancelled.");
				}
			}
		});
		
		saveS.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				int returnVal = fileC.showSaveDialog(mainApp.this);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					
					//Open or create SALES_LIST.txt.
					try {
						
						File f_purchased = fileC.getSelectedFile();
						
						FileWriter fw = new FileWriter(f_purchased.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write("SALES_LIST");
						bw.newLine();
						bw.write("{");
						bw.newLine();
						bw.write("\t");
						bw.newLine();
						for(int i = 0; i < p_list.size(); i++){
							
							write(p_list.get(i), bw);
						
						}
						
						bw.write("\tTOTAL_SALES_PRICE " + total_purchase_price);
						bw.newLine();
						bw.write("\t");
						bw.newLine();
						bw.write("}");
						bw.close();

					} catch (IOException er) {
						er.printStackTrace();
					}
					
				}else{
					resultArea3.setText("Save command cancelled.");
				}
				
			}
		});
		
		nextSal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					viewDet3.setEnabled(true);
					nextSal.setText("Next Sale");
					resultArea3.setText("Total purchase price: " + total_purchase_price);
					posS++;
					displaySaleInfo();
					if(posS > 0){prevSal.setEnabled(true);}
					if(posS >= p_list.size() - 1){nextSal.setEnabled(false);}
				}catch(IndexOutOfBoundsException ioobe){
					resultArea3.setText("Can' t access sale list!");
				}
			}
		});
		
		prevSal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					nextSal.setEnabled(true);
					resultArea3.setText("Total purchase price: " + total_purchase_price);
					posS--;
					displaySaleInfo();
					if(posS == 0){prevSal.setEnabled(false);}
				}catch(IndexOutOfBoundsException ioobe){
					resultArea3.setText("Can' t access sale list!");
				}
			}
		});
		
		viewDet3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saleInfo.setText(p_list.get(posS).toString());
			}
		});
		
		saleSearch.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				saleSearch.setText("");
			}
		});
		
		searchS.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				found = false;
				
				if(!((saleSearch.getText().equals("")) || (saleSearch.getText().equals("Type the name of the order here.")))){
					for(int i = 0; i < p_list.size(); i++){
						if(p_list.get(i).getName().equals(saleSearch.getText())){
							found = true;
							posS = i;
						}
					}
				}
				
				if(found){
					if(posS == p_list.size() - 1){	//Last item.
						nextSal.setEnabled(false);
						if(p_list.size() > 1){
							prevOrd.setEnabled(true);
						}
					}else{
						if(posS == 0){
							prevSal.setEnabled(false);
							if(p_list.size() > 1){
								nextSal.setEnabled(true);
							}
						}
					}
					displaySaleInfo();
				}else{
					saleInfo.setText("Invalid selection.\nDevice with name\n'" + saleSearch.getText() +
					"'\nnot found.");
				}
			}
		});
		
		thirdTab.add(openS);
		thirdTab.add(saveS);
		thirdTab.add(nextSal);
		thirdTab.add(prevSal);
		thirdTab.add(viewDet3);
		thirdTab.add(resultArea3);
		thirdTab.add(listScroller3);
		thirdTab.add(imageViewer3);
		thirdTab.add(saleSearch);
		thirdTab.add(searchS);
		
		//ADD THE THIRD TAB TO TABBEDPANEL
		tabPane.add("SALES", thirdTab);
		tabPane.setEnabledAt(2, false);
		
		//ADD TABBEDPANEL TO FRAME
		mainPanel.add(tabPane);
		
	}
	
	public void displayDeviceInfo(){
		Item dev = items.get(posD);
		deviceInfo.setText("Model: " + dev.getModel() + "\nItem Type: " + dev.getClass().getName() +
		"\nManufacturer: " + dev.getConstructor() + "\nModel Year: " +
		dev.getYear() + "\nAvailable: " + dev.getQuantity() + " pieces");
		imageViewer.setIcon((new ImageIcon(((new ImageIcon("Images/" + dev.getModel() + ".png")).getImage()).getScaledInstance(280, 280, java.awt.Image.SCALE_SMOOTH))));
	}
	
	public void displayOrderInfo(){
		Item dev = order_list.get(posO).getDevice();
		orderInfo.setText("Model: " + dev.getModel() + "\nItem Type: " + dev.getClass().getName() +
		"\nManufacturer: " + dev.getConstructor() + "\nModel Year: " +
		dev.getYear() + "\nAvailable: " + dev.getQuantity() + " pieces");
		imageViewer2.setIcon((new ImageIcon(((new ImageIcon("Images/" + dev.getModel() + ".png")).getImage()).getScaledInstance(280, 280, java.awt.Image.SCALE_SMOOTH))));
	}
	
	public void displaySaleInfo(){
		Item dev = p_list.get(posS).getDevice();
		saleInfo.setText("Model: " + dev.getModel() + "\nItem Type: " + dev.getClass().getName() +
		"\nManufacturer: " + dev.getConstructor() + "\nModel Year: " +
		dev.getYear() + "\nAvailable: " + dev.getQuantity() + " pieces");
		imageViewer3.setIcon((new ImageIcon(((new ImageIcon("Images/" + dev.getModel() + ".png")).getImage()).getScaledInstance(280, 280, java.awt.Image.SCALE_SMOOTH))));
	}
	
	public void orderDeviceMethod(){
		
		String field = "Name:", defaultText = null;
		String[] input = {null, null, null, null};
		boolean cancelled = false;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		
		for (int i = 0; i < 4; i++){
			
			switch(i){
				case 1:	field = "Phone:";																							break;
				case 2:	field = "Order date:";										defaultText = dateFormat.format(cal.getTime());	break;	
				case 3:	field = "Arrive date:";	cal.add(Calendar.WEEK_OF_MONTH, 2); defaultText = dateFormat.format(cal.getTime());	break;
			}
			
			input[i] = (String)JOptionPane.showInputDialog(mainApp.this, field, "Order info", JOptionPane.QUESTION_MESSAGE, null, null, defaultText);
			
			if (input[i] == null){	//Cancel button.
				cancelled = true;
				break;
			}
			
		}
		
		if(!cancelled){
			order_code++;
			Order o = new Order(items.get(posD), input[0], input[1], input[2], input[3], order_code);
			order_list.add(o);
			total_order_price += items.get(posD).getPrice();
			resultArea.setText("Ordered!");
			saveO.setEnabled(true);
			nextOrd.setEnabled(true);
			searchO.setEnabled(true);
			orderSearch.setEnabled(true);
		}else{
			resultArea.setText("Order cancelled.");
		}
	}
	
	public void purchaseDeviceMethod(JTabbedPane tp){
		
		String field = "Name:", defaultText = null;
		String[] input = {null, null, null};
		boolean cancelled = false;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		
		for (int i = 0; i < 3; i++){
			
			switch(i){
				case 1:	field = "Phone:";															break;
				case 2:	field = "Purchase date:";	defaultText = dateFormat.format(cal.getTime());	break;
			}
			
			input[i] = (String)JOptionPane.showInputDialog(mainApp.this, field, "Purchase info", JOptionPane.QUESTION_MESSAGE, null, null, defaultText);
			
			if (input[i] == null){	//Cancel button.
				cancelled = true;
				break;
			}
			
		}
		
		if(!cancelled){
			purchase_code++;
			Purchased p = new Purchased(items.get(posD), input[0], input[1], input[2], purchase_code);
			p_list.add(p);
			total_purchase_price += items.get(posD).getPrice();
			if (tp.getSelectedIndex() == 0){
				resultArea.setText("Purchased!");
			}else{
				resultArea2.setText("Purchased!");
			}
			saveS.setEnabled(true);
			nextSal.setEnabled(true);
			searchS.setEnabled(true);
			saleSearch.setEnabled(true);
			displayDeviceInfo();
		}else{
			if (tp.getSelectedIndex() == 0){
				resultArea.setText("Purchase cancelled.");
			}else{
				resultArea2.setText("Purchase cancelled.");
			}
		}
	}
	
	public void mouseExited(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mousePressed(MouseEvent event){}
	
	public static void main(String[] args) {
		
		mainApp main = new mainApp();
		main.setVisible(true);

	}
	
	//This method writes in a file the device specs.
	public static void write(Action action, BufferedWriter bw){
		
		try {
		
			Item device = action.getDevice();
		
			boolean order = false;
	
			if(action instanceof Order){
				order = true;
			}
			
			String text = action.getClass().getName().toUpperCase();
			if(text.equals("PURCHASED")){ text = "SALE"; }
		
			bw.write("\t" + text);
			bw.newLine();
			bw.write("\t{");
			
			bw.newLine();
			if(order){
				bw.write("\t\t" + text + "_NUMBER " + ((Order)action).getOrderCode());
			}else{
				bw.write("\t\t" + text + "_NUMBER " + ((Purchased)action).getPurchaseCode());
			}
			
			bw.newLine();
			if(order){
				bw.write("\t\t" + text + "_DATE " + ((Order)action).getOrderDate());
			}else{
				bw.write("\t\t" + text + "_DATE " + ((Purchased)action).getDate());
			}
		
			if(order){
				bw.newLine();
				bw.write("\t\tDELIVERY_DATE " + ((Order)action).getArriveDate());
			}
		
			bw.newLine();
			bw.write("\t\tNAME " + action.getName());
		
			bw.newLine();
			bw.write("\t\tPHONE " + action.getPhone());
		
			if(order){
				String status = "EXPECTED";
				if(((Order)action).hasArrived()){
					status = "ARRIVED";
				}
				bw.newLine();
				bw.write("\t\tSTATUS " + status);
			}
			
			bw.newLine();
			bw.write("\t\tITEM_TYPE " + device.getClass().getName());
	
			bw.newLine();
			bw.write("\t\tCODE " + device.getID());
	
			bw.newLine();
			bw.write("\t\tMODEL " + device.getModel());
	
			bw.newLine();
			bw.write("\t\tYEAR " + device.getYear());
	
			bw.newLine();
			bw.write("\t\tMANUFACTURER " + device.getConstructor());
		
			bw.newLine();
			bw.write("\t\tPRICE " + device.getPrice());
	
			bw.newLine();
			bw.write("\t\tPIECES " + device.getQuantity());
	
			if(device instanceof PicAndSound){
		
				String PicAndSound_Type = ((PicAndSound)device).getType();
		
				if (device instanceof TV){
		
					bw.newLine();
					bw.write("\t\tPANEL_TYPE " + PicAndSound_Type);
			
					bw.newLine();
					bw.write("\t\tRESOLOUTION " + ((TV)device).getResolution());
			
					bw.newLine();
					bw.write("\t\tDIMENSIONS " + ((TV)device).getDimension());
				
					bw.newLine();
					bw.write("\t\tINTERFACES " + ((TV)device).getPorts());
			
				}else if(device instanceof BR_DVD){
			
					bw.newLine();
					bw.write("\t\tPLAYER_TYPE " + PicAndSound_Type);
			
					bw.newLine();
					bw.write("\t\tRESOLOUTION " + ((TV)device).getResolution());
			
					bw.newLine();
					bw.write("\t\tFORMAT " + ((BR_DVD)device).getFormat());
			
				}else{
			
					bw.newLine();
					bw.write("\t\tCAMERA_TYPE " + PicAndSound_Type);
			
					bw.newLine();
					bw.write("\t\tMEGAPIXELS " + ((Camera)device).getMegaPix());
			
					bw.newLine();
					bw.write("\t\tOPTICAL_ZOOM " + ((Camera)device).getOpticalZoom());
			
					bw.newLine();
					bw.write("\t\tDIGITAL_ZOOM " + ((Camera)device).getDigitalZoom());
			
					bw.newLine();
					bw.write("\t\tSCREEN_SIZE " + ((Camera)device).getScreenSize());
			
				}
		
			}else if(device instanceof Gaming){
		
				bw.newLine();
				bw.write("\t\tCONSOLE_TYPE " + ((Gaming)device).getType());
		
				bw.newLine();
				bw.write("\t\tCPU " + ((Gaming)device).getCPU());
		
				bw.newLine();
				bw.write("\t\tGPU " + ((Gaming)device).getGPU());
		
				bw.newLine();
				bw.write("\t\tSOUND " + ((Gaming)device).getSound());
		
				bw.newLine();
				bw.write("\t\tHARD_DISK " + ((Gaming)device).getHD());
			
			}else{
	
				bw.newLine();
				bw.write("\t\tENERGY_CLASS " + ((HomeAppliance)device).getEC());
		
				if(device instanceof Fridge){
		
					bw.newLine();
					bw.write("\t\tFRIDGE_TYPE " + ((Fridge)device).getType());
			
					bw.newLine();
					bw.write("\t\tFREEZER_CAPACITY " + ((Fridge)device).getFreezerCap() + "L");
			
					bw.newLine();
					bw.write("\t\tREFRIGERATOR_CAPACITY " + ((Fridge)device).getRefrigeratorCap() + "L");
				
					}else{
			
					bw.newLine();
					bw.write("\t\tCAPACITY " + ((WashingMachine)device).getCapacity() + "L");
			
					bw.newLine();
					bw.write("\t\tSPIN_SPEED " + ((WashingMachine)device).getBends() + " RPM");
				
				}
		
			}
	
			bw.newLine();
			bw.write("\t}");
			bw.newLine();
			bw.write("\t");
			bw.newLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
