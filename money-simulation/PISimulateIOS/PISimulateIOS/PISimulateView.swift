//
//  PISimulateView.swift
//  PISimulateIOS
//
//  Created by Jacob Su on 10/12/17.
//  Copyright © 2017 Jacob Su. All rights reserved.
//

import UIKit

class PISimulateView: UIView {

    var w : CGFloat = 0
    
    var needReDraw = true
    var px : CGFloat = 0.0
    var py : CGFloat = 0.0
    
    override var bounds: CGRect {
        didSet {
            needReDraw = true
            setNeedsDisplay()
        }
    }
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
        if bounds.width == 0 {
            return
        }
        if needReDraw {
            needReDraw = false
            drawFrame(rect: rect)
            return
        }
        
        if w == 0 {
            w = bounds.width < bounds.height ? bounds.width : bounds.height
        }
        
        renderAt(x: px, y: py)
    }
    
    func renderRandomPoint() -> Void {
        let x = CGFloat(arc4random_uniform(UInt32(w)))
        let y = CGFloat(arc4random_uniform(UInt32(w)))
        
        px = x
        py = y
        
        setNeedsDisplay()
    }
    
    func renderAt(x: CGFloat, y: CGFloat) -> Void {
//        print("render at (x, y) = \(x) \(y)")
        let con = UIGraphicsGetCurrentContext()
        guard let ctx = con else {
            return
        }
        
        if (insideCircle(x: x, y: y)) {
            ctx.setFillColor(UIColor.red.cgColor)
        } else {
            ctx.setFillColor(UIColor.blue.cgColor)
        }
        
        ctx.fillEllipse(in: CGRect(x: x, y: y, width: 4, height: 4))
    }
 
    private func drawFrame(rect: CGRect) {
        print("draw frame")
        w = rect.width <= rect.height ? rect.width : rect.height
        
        let con = UIGraphicsGetCurrentContext()
        guard let ctx = con else {
            return
        }
        
        ctx.clear(rect)
        ctx.setFillColor(UIColor.white.cgColor)
        ctx.fill(rect)
        ctx.setStrokeColor(UIColor.black.cgColor)
        ctx.setLineWidth(2)
        ctx.stroke(CGRect(x: 0, y: 0, width: w, height: w))
    }
    
    private func insideCircle(x: CGFloat, y: CGFloat) -> Bool {
        let r = w / 2
        return (x - r) * (x - r) + (y - r) * (y - r) < r * r;
    }

}
